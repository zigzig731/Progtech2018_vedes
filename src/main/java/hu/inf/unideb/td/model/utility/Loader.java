package hu.inf.unideb.td.model.utility;

import de.matthiasmann.twl.utils.PNGDecoder;
import hu.inf.unideb.td.model.Model;
import hu.inf.unideb.td.model.SessionManagement.Session;
import hu.inf.unideb.td.model.SessionManagement.Wave;
import hu.inf.unideb.td.model.SessionManagement.WaveComponent;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.ARBTextureFilterAnisotropic;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL46.GL_TEXTURE_MAX_ANISOTROPY;

/**
 * Ez az osztály különböző fájlok betöltésére szolgál.
 */
public class Loader {
    /**
     * A programban valaha beolvasott összes VAO-t tárolja.
     */
    private List<Integer> vaos = new ArrayList<Integer>();
    /**
     * A programban valaha beolvasott összes VBO-t tárolja.
     */
    private List<Integer> vbos = new ArrayList<Integer>();
    /**
     * A programban valaha beolvasott összes textúrát tárolja.
     */
    private List<Integer> textures = new ArrayList<Integer>();

    /**
     * A modellek betöltésére szolgáló metódus.
     * A modell adatait egy VAO-ba tölti
     * @param positions A vao-ba töltendő vertexpoziciók.
     * @param UVs A vao-ba töltendő UV koordináták.
     * @param normals A vao-ba töltendő normálvektorok.
     * @param tangents A vao-ba töltendő tangentek.
     * @param indices A vao-ba töltendő indexek.
     * @return A beolvasott model/VAO opengl által használt ID-ja.
     */
    public Model loadToVAO(float[] positions, float[] UVs, float[] normals, float[] tangents, int[] indices) {
        int vao = createVAO();
        bindindIndicesBuffer(indices);
        storeDataInAttributeList(0, 3, positions);
        storeDataInAttributeList(1, 2, UVs);
        storeDataInAttributeList(2, 3, normals);
        storeDataInAttributeList(3, 3, tangents);
        unbindVAO();
        return new Model(vao, indices.length);
    }

    /**
     * A textúrák betöltésére szolgáló metódus.
     * @param filePath A fájl elérési útvonala.
     * @return A beolvasott textúra opengl által használt ID-ja.
     */
    public int loadTexture(String filePath) {
        int textureID = 0;
        try {

            InputStream in = getClass().getClassLoader().getResourceAsStream(filePath);
           // InputStream in = new FileInputStream(getClass().getClassLoader().getResource(filePath).getFile());
            PNGDecoder decoder = new PNGDecoder(in);
            ByteBuffer buffer = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
            decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
            buffer.flip();
            textureID = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, textureID);

            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

            glTexParameterf(GL_TEXTURE_2D,GL_TEXTURE_MAX_ANISOTROPY,16.0f);

            // WRAP OR NOT TO WRAP, THAT IS THE QUESTION
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);


            // ME DOEST SMOOTH OR SHARP? SHARP!
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

            // MIP MAP!
            glGenerateMipmap(GL_TEXTURE_2D);
            // MAKE!
            glBindTexture(GL_TEXTURE_2D, 0);
            in.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return textureID;
    }

    /**
     * A VAO-k létrehozását végző metódus.
     * @return A létrehozott VAO ID-je.
     */
    private int createVAO() {
        int vao = glGenVertexArrays();
        glBindVertexArray(vao);
        vaos.add(vao);
        return vao;
    }

    /**
     * Az adatok attribútlistában való tárolását megvalósitó függvénye.
     * @param attribute Az attribútum indexe.
     * @param size Az adatok egységmérete.
     * @param data Az eltárolni kivánt adat.
     */
    private void storeDataInAttributeList(int attribute, int size, float[] data) {
        int vbo = glGenBuffers();
        vbos.add(vbo);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glVertexAttribPointer(attribute, size, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }


    /**
     * Az indexbufferek létrehozását végző függvény.
     * @param indices Az eltárolni kivánt indexek.
     */
    private void bindindIndicesBuffer(int[] indices) {
        int vbo = glGenBuffers();
        vbos.add(vbo);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vbo);
        IntBuffer buffer = BufferUtils.createIntBuffer(indices.length);
        buffer.put(indices);
        buffer.flip();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
    }

    /**
     * A VAO-k törléséért felelős függvény.
     */
    private void unbindVAO() {
        glBindVertexArray(0);
    }

    /**
     * A shaderek betöltését és forditását végző függvény.
     * @param file A fájl elérési útvonala.
     * @param type A betölteni kivánt shader tipusa.
     * @return A beolvasott shader ID-ja.
     */
    public int loadShader(String file, int type) {
        StringBuilder shaderSource = new StringBuilder();
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                shaderSource.append(line).append("//\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader!");
            System.exit(-1);
        }
        return shaderID;
    }

    /**
     * A Sessionök betöltésére szolgáló függvény.
     * @param name A betölteni kivánt xml neve.
     * @return A betöltött Session.
     * @see Session
     */
    public Session loadSession(String name)
    {
        Session session = new Session();
        try {
          //  FileReader fileReader=new FileReader(getClass().getClassLoader().getResource("Sessions/"+name+".xml").getFile());
            InputStream in = getClass().getClassLoader().getResourceAsStream("Sessions/"+name+".xml");
            JAXBContext context = JAXBContext.newInstance(Session.class,Wave.class, WaveComponent.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            session=(Session)unmarshaller.unmarshal(in);
            //System.out.println(session.getWaves().get(1).getWaveComponents().get(1).getType());
        //    fileReader.close();
            in.close();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return session;
    }

    /**
     *A betöltött fájlok felszabaditását végző függvény.
     */
    public void cleanup() {
        for (int vao : vaos) {
            glDeleteVertexArrays(vao);
        }
        for (int vbo : vbos) {
            glDeleteBuffers(vbo);
        }
        for (int texture : textures) {
            glDeleteTextures(texture);
        }
    }
}