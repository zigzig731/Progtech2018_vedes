		package hu.inf.unideb.td.model.normalMappingObjConverter;

		import hu.inf.unideb.td.model.utility.Loader;
        import hu.inf.unideb.td.model.Model;
        import org.joml.Vector2f;
		import org.joml.Vector3f;

        import java.io.*;
        import java.util.ArrayList;
		import java.util.List;

        /**
         * Az obj fileok betöltésére szolgáló osztály.
         */
        public class NormalMappedObjLoader {

            /**
             * Az obj file elérési útjának része.
             */
            private static final String RES_LOC = "Models/";

            /**
             * Az obj filet betöltő metódus.
             * @param objFileName Fájlnév
             * @param loader A loader osztály mellyel betöltjük az objt.
             * @return A betöltött modell.
             */
            public static Model loadOBJ(String objFileName, Loader loader) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(NormalMappedObjLoader.class.getClassLoader().getResourceAsStream(RES_LOC + objFileName + ".obj")));
                String line;
                List<VertexNM> vertices = new ArrayList<VertexNM>();
                List<Vector2f> textures = new ArrayList<Vector2f>();
                List<Vector3f> normals = new ArrayList<Vector3f>();
                List<Integer> indices = new ArrayList<Integer>();
                try {
                    while (true) {
                        line = reader.readLine();
                        if (line.startsWith("v ")) {
                            String[] currentLine = line.split(" ");
                            Vector3f vertex = new Vector3f((float) Float.valueOf(currentLine[1]),
                                    (float) Float.valueOf(currentLine[2]),
                                    (float) Float.valueOf(currentLine[3]));
                            VertexNM newVertex = new VertexNM(vertices.size(), vertex);
                            vertices.add(newVertex);

                        } else if (line.startsWith("vt ")) {
                            String[] currentLine = line.split(" ");
                            Vector2f texture = new Vector2f((float) Float.valueOf(currentLine[1]),
                                    (float) Float.valueOf(currentLine[2]));
                            textures.add(texture);
                        } else if (line.startsWith("vn ")) {
                            String[] currentLine = line.split(" ");
                            Vector3f normal = new Vector3f((float) Float.valueOf(currentLine[1]),
                                    (float) Float.valueOf(currentLine[2]),
                                    (float) Float.valueOf(currentLine[3]));
                            normals.add(normal);
                        } else if (line.startsWith("f ")) {
                            break;
                        }
                    }
                    while (line != null && line.startsWith("f ")) {
                        String[] currentLine = line.split(" ");
                        String[] vertex1 = currentLine[1].split("/");
                        String[] vertex2 = currentLine[2].split("/");
                        String[] vertex3 = currentLine[3].split("/");
                        VertexNM v0 = processVertex(vertex1, vertices, indices);
                        VertexNM v1 = processVertex(vertex2, vertices, indices);
                        VertexNM v2 = processVertex(vertex3, vertices, indices);
                        calculateTangents(v0, v1, v2, textures);//NEW
                        line = reader.readLine();
                    }
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Error reading the file");
                }
                removeUnusedVertices(vertices);
                float[] verticesArray = new float[vertices.size() * 3];
                float[] texturesArray = new float[vertices.size() * 2];
                float[] normalsArray = new float[vertices.size() * 3];
                float[] tangentsArray = new float[vertices.size() * 3];
                float furthest = convertDataToArrays(vertices, textures, normals, verticesArray,
                        texturesArray, normalsArray, tangentsArray);
                int[] indicesArray = convertIndicesListToArray(indices);

                return loader.loadToVAO(verticesArray, texturesArray, normalsArray, tangentsArray, indicesArray);
            }

            /**
             * A tangentek kiszámitását végző függvény.
             * @param v0 Első térpont
             * @param v1 Második térpont
             * @param v2 Harmadik térpont
             * @param textures Textúrakoordináták
             */
            private static void calculateTangents(VertexNM v0, VertexNM v1, VertexNM v2,
                                                  List<Vector2f> textures) {
                Vector3f v0p = new Vector3f(v0.getPosition().x,v0.getPosition().y,v0.getPosition().z);
                Vector3f v1p = new Vector3f(v1.getPosition().x,v1.getPosition().y,v1.getPosition().z);
                Vector3f v2p = new Vector3f(v2.getPosition().x,v2.getPosition().y,v2.getPosition().z);
                Vector3f delatPos1;
                delatPos1=v1p.sub(v0p);
                Vector3f delatPos2;
                delatPos2= v2p.sub(v0p);
                Vector2f uv0 = new Vector2f(textures.get(v0.getTextureIndex()).x,textures.get(v0.getTextureIndex()).y);
                Vector2f uv1 = new Vector2f(textures.get(v1.getTextureIndex()).x,textures.get(v1.getTextureIndex()).y);
                Vector2f uv2 = new Vector2f(textures.get(v2.getTextureIndex()).x,textures.get(v2.getTextureIndex()).y);
                Vector2f deltaUv1;
                deltaUv1=uv1.sub(uv0);
                Vector2f deltaUv2;
                deltaUv2 = uv2.sub(uv0);
                float r = 1.0f / (deltaUv1.x * deltaUv2.y - deltaUv1.y * deltaUv2.x);
                delatPos1.mul(deltaUv2.y);
                delatPos2.mul(deltaUv1.y);
                Vector3f tangent = delatPos1.sub(delatPos2);
                tangent.mul(r);
                v0.addTangent(tangent);
                v1.addTangent(tangent);
                v2.addTangent(tangent);
            }

            /**
             * Egy vertex feldolgozását végző metódus.
             * @param vertex Egy vertex adatai.
             * @param vertices A vertexek listája.
             * @param indices Az indexek listája.
             * @return Egy feldolgozott vertex.
             */
            private static VertexNM processVertex(String[] vertex, List<VertexNM> vertices,
                                                  List<Integer> indices) {
                int index = Integer.parseInt(vertex[0]) - 1;
                VertexNM currentVertex = vertices.get(index);
                int textureIndex = Integer.parseInt(vertex[1]) - 1;
                int normalIndex = Integer.parseInt(vertex[2]) - 1;
                if (!currentVertex.isSet()) {
                    currentVertex.setTextureIndex(textureIndex);
                    currentVertex.setNormalIndex(normalIndex);
                    indices.add(index);
                    return currentVertex;
                } else {
                    return dealWithAlreadyProcessedVertex(currentVertex, textureIndex, normalIndex, indices,
                            vertices);
                }
            }

            /**
             * Az indexek listából tömbé konvertálását végző metódus.
             * @param indices Az indexek listája.
             * @return Az indexek tömbje.
             */
            private static int[] convertIndicesListToArray(List<Integer> indices) {
                int[] indicesArray = new int[indices.size()];
                for (int i = 0; i < indicesArray.length; i++) {
                    indicesArray[i] = indices.get(i);
                }
                return indicesArray;
            }

            /**
             * Adatok tömbökké konvertálását végző metódus.
             * @param vertices A vertexek listája.
             * @param textures Textúrakoordináták litsája.
             * @param normals Normálisok listája.
             * @param verticesArray Vertexek tömbje.
             * @param texturesArray Textúrakoordináták tömbje.
             * @param normalsArray Normálisok tömbje.
             * @param tangentsArray Tangentek tömbje.
             * @return A konvertelt adat.
             */
            private static float convertDataToArrays(List<VertexNM> vertices, List<Vector2f> textures,
                                                     List<Vector3f> normals, float[] verticesArray, float[] texturesArray,
                                                     float[] normalsArray, float[] tangentsArray) {
                float furthestPoint = 0;
                for (int i = 0; i < vertices.size(); i++) {
                    VertexNM currentVertex = vertices.get(i);
                    if (currentVertex.getLength() > furthestPoint) {
                        furthestPoint = currentVertex.getLength();
                    }
                    Vector3f position = currentVertex.getPosition();
                    Vector2f textureCoord = textures.get(currentVertex.getTextureIndex());
                    Vector3f normalVector = normals.get(currentVertex.getNormalIndex());
                    //currentVertex.averageTangents();
                    Vector3f tangent = currentVertex.getAverageTangent();
                    verticesArray[i * 3] = position.x;
                    verticesArray[i * 3 + 1] = position.y;
                    verticesArray[i * 3 + 2] = position.z;
                    texturesArray[i * 2] = textureCoord.x;
                    texturesArray[i * 2 + 1] = 1 - textureCoord.y;
                    normalsArray[i * 3] = normalVector.x;
                    normalsArray[i * 3 + 1] = normalVector.y;
                    normalsArray[i * 3 + 2] = normalVector.z;
                    tangentsArray[i * 3] = tangent.x;
                    tangentsArray[i * 3 + 1] = tangent.y;
                    tangentsArray[i * 3 + 2] = tangent.z;

                }
                return furthestPoint;
            }

            /**
             * A már feldolgozott vertexek kezelését végző metódus.
             * @param previousVertex Előző térpont.
             * @param newTextureIndex Új textúra index.
             * @param newNormalIndex Új normális index.
             * @param indices Indexek
             * @param vertices Vertexek
             * @return Egy vertex.
             */
            private static VertexNM dealWithAlreadyProcessedVertex(VertexNM previousVertex, int newTextureIndex,
                                                                   int newNormalIndex, List<Integer> indices, List<VertexNM> vertices) {
                if (previousVertex.hasSameTextureAndNormal(newTextureIndex, newNormalIndex)) {
                    indices.add(previousVertex.getIndex());
                    return previousVertex;
                } else {
                    VertexNM anotherVertex = previousVertex.getDuplicateVertex();
                    if (anotherVertex != null) {
                        return dealWithAlreadyProcessedVertex(anotherVertex, newTextureIndex,
                                newNormalIndex, indices, vertices);
                    } else {
                        VertexNM duplicateVertex = previousVertex.duplicate(vertices.size());//NEW
                        duplicateVertex.setTextureIndex(newTextureIndex);
                        duplicateVertex.setNormalIndex(newNormalIndex);
                        previousVertex.setDuplicateVertex(duplicateVertex);
                        vertices.add(duplicateVertex);
                        indices.add(duplicateVertex.getIndex());
                        return duplicateVertex;
                    }
                }
            }

            /**
             * Nem használt vertexek törlését végző metódus.
             * @param vertices A vertexek listája.
             */
            private static void removeUnusedVertices(List<VertexNM> vertices) {
                for (VertexNM vertex : vertices) {
                    vertex.averageTangents();
                    if (!vertex.isSet()) {
                        vertex.setTextureIndex(0);
                        vertex.setNormalIndex(0);
                    }
                }
            }

        }