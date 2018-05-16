		package hu.inf.unideb.td.model.normalMappingObjConverter;

		import org.joml.Vector3f;
		import org.lwjgl.Sys;

		import java.util.ArrayList;
		import java.util.List;

        /**
         * Egy vertex adatainak tárolására szolgáló osztály.
         */
        public class VertexNM {
            /**
             * kezdő index.
             */
            private static final int NO_INDEX = -1;
            /**
             * Vertexpozició.
             */
            private Vector3f position;
            /**
             * A textúra indexe.
             */
            private int textureIndex = NO_INDEX;
            /**
             * A normális indexe.
             */
            private int normalIndex = NO_INDEX;
            /**
             * Duplikált e a vertex.
             */
            private VertexNM duplicateVertex = null;
            /**
             * A vertex indexe.
             */
            private int index;
            /**
             * A vertex hossza.
             */
            private float length;
            /**
             * A vertex tangentjei.
             */
            private List<Vector3f> tangents = new ArrayList<Vector3f>();
            /**
             * A vertex tangentjeinek átlaga.
             */
            private Vector3f averagedTangent = new Vector3f(0, 0, 0);

            /**
             * Egy vertex konstruktora.
             * @param index Index.
             * @param position Pozició.
             */
            protected VertexNM(int index, Vector3f position){
                this.index = index;
                this.position = position;
                this.length = position.length();
            }

            /**
             * A tangent hozzáadását lehetővé tevő metódus.
             * @param tangent A hozzáadni kivánt tangent.
             */
            protected void addTangent(Vector3f tangent){
                tangents.add(tangent);
                //System.out.println(tangent);
            }

            /**
             * A duplikált létrehozásáért felelős metódus.
             * @param newIndex Az új vertex indexe.
             * @return Az új vertex
             */
            protected VertexNM duplicate(int newIndex){
                VertexNM vertex = new VertexNM(newIndex, position);
                vertex.tangents = this.tangents;
                return vertex;
            }

            /**
             * Az átlagolt tangent kiszámolását biztositó metódus.
             */
            protected void averageTangents(){
                 for(Vector3f tangent : tangents){
                    averagedTangent.add(tangent);
                }
                averagedTangent.normalize();
            }

            /**
             * Az általános tangent lekérését bizotsitó metódus.
             * @return Az átlag tangent vektor.
             */
            protected Vector3f getAverageTangent(){
                return averagedTangent;
            }

            /**
             * A vertex indexének getterje.
             * @return A vertex indexe.
             */
            protected int getIndex(){
                return index;
            }

            /**
             * A vertex hosszának getterje.
             * @return A vertex hossza.
             */
            protected float getLength(){
                return length;
            }

            /**
             * Azt vizsgálja hogy be volt e már a vertex korábban állitva.
             * @return igen vagy nem igazságérték.
             */
            protected boolean isSet(){
                return textureIndex!=NO_INDEX && normalIndex!=NO_INDEX;
            }

            /**
             * Ugyan az e a normál és textúrakoordinátája mint egy másik térpont.
             * @param textureIndexOther A másik vertex textúraindexe.
             * @param normalIndexOther A másik térpont normális indexe.
             * @return Igen ha ugyan az, nem ha nem ugyan az, (true/false) érték.
             */
            protected boolean hasSameTextureAndNormal(int textureIndexOther,int normalIndexOther){
                return textureIndexOther==textureIndex && normalIndexOther==normalIndex;
            }

            /**
             * A textúraindex setterje.
             * @param textureIndex beállitani kivánt textúraindex.
             */
            protected void setTextureIndex(int textureIndex){
                this.textureIndex = textureIndex;
            }
            /**
             * A normálisindex setterje.
             * @param normalIndex beállitani kivánt normálilsindexet.
             */
            protected void setNormalIndex(int normalIndex){
                this.normalIndex = normalIndex;
            }

            /**
             * A vertex poziciójának getterje.
             * @return A vertex poziciója.
             */
            protected Vector3f getPosition() {
                return position;
            }

            /**
             * A vertex textúraindexének lekérését biztositó getter.
             * @return A vertex textúraindexe.
             */
            protected int getTextureIndex() {
                return textureIndex;
            }

            /**
             * A vertex normálisindexét lekérő metódus.
             * @return A vertex normálindexe.
             */
            protected int getNormalIndex() {
                return normalIndex;
            }

            /**
             * A vertex duplikáltvertexének lekérése.
             * @return A vertex párja.
             */
            protected VertexNM getDuplicateVertex() {
                return duplicateVertex;
            }

            /**
             * A vertex duplicatevertexének beállitása.
             * @param duplicateVertex A beállitandó vertex.
             */
            protected void setDuplicateVertex(VertexNM duplicateVertex) {
                this.duplicateVertex = duplicateVertex;
            }

        }