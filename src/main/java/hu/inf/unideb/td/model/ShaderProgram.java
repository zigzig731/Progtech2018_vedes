package hu.inf.unideb.td.model;

import hu.inf.unideb.td.model.utility.Loader;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;

/**
 * Minden shader alap osztálya.
 */
public abstract class ShaderProgram {
    /**
     * A shader ID-ja.
     */
    private int programID;
    /**
     * A vertexshader ID-ja.
     */
    private int vertexShaderID;
    /**
     * A fragmentshader ID-ja.
     */
    private int fragmentShaderID;

    /**
     * A mátrixok betöltésénél használt floatbuffer.
     */
    private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

    /**
     * A shaderprogram egy konstruktora.
     * @param vertexfile A vertexshader elérési útja.
     * @param fragmentfile A fragmentshader elérési útja.
     */
    public ShaderProgram(String vertexfile, String fragmentfile) {
        Loader loader=new Loader();
        vertexShaderID = loader.loadShader(vertexfile, GL20.GL_VERTEX_SHADER);
        fragmentShaderID = loader.loadShader(fragmentfile, GL20.GL_FRAGMENT_SHADER);
        programID = GL20.glCreateProgram();
        GL20.glAttachShader(programID, vertexShaderID);
        GL20.glAttachShader(programID, fragmentShaderID);
        bindAttributes();
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);
        getAllUniformLocations();
    }

    /**
     * A shaderprogram egy absztrakt metódusa mej majd a leszármazottakban lesz kifejtfe.
     */
    protected abstract void getAllUniformLocations();

    /**
     * Ezzel a metódussal le tudjuk kérdezni egy uniform változó helyét a shaderben.
     * @param uniformName A változó neve.
     * @return A változó helye a shaderben.
     */
    protected int getuniformlocation(String uniformName) {
        return GL20.glGetUniformLocation(programID, uniformName);
    }

    /**
     * A shadert elinditó metódus.
     */
    public void start() {
        GL20.glUseProgram(programID);
    }

    /**
     * A shadert megállitó metódsu.
     */
    public void stop() {
        GL20.glUseProgram(0);
    }

    /**
     * A shader felszabaditását végzi.
     */
    public void cleanup() {
        stop();
        GL20.glDetachShader(programID, vertexShaderID);
        GL20.glDetachShader(programID, fragmentShaderID);
        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
        GL20.glDeleteProgram(programID);
    }

    /**
     * A shaderprogram egy absztrakt metódusa mej majd a leszármazottakban lesz kifejtfe.
     */
    protected abstract void bindAttributes();

    /**
     * Ez a metódus attribútumokat csatol a shaderünköz.
     * @param attribute Az attribútum értéke.
     * @param name Az attribútum neve.
     */
    protected void bindAttributes(int attribute, String name) {
        GL20.glBindAttribLocation(programID, attribute, name);
    }

    /**
     * Egy float betöltését végzi a shaderbe.
     * @param location A betöltés helye.
     * @param value Az érték.
     */
    protected void loadFloat(int location, float value) {
        GL20.glUniform1f(location, value);
    }
    /**
     * Egy vector betöltését végzi a shaderbe.
     * @param location A betöltés helye.
     * @param value Az érték.
     */
    protected void loadVector(int location, Vector3f value) {
        GL20.glUniform3f(location, value.x, value.y, value.z);
    }
    /**
     * Egy boolean betöltését végzi a shaderbe.
     * @param location A betöltés helye.
     * @param value Az érték.
     */
    protected void loadBoolean(int location, boolean value) {
        float toLoad = 0;
        if (value) {
            toLoad = 1;
        }
        GL20.glUniform1f(location, toLoad);
    }
    /**
     * Egy mátrix betöltését végzi a shaderbe.
     * @param location A betöltés helye.
     * @param matrix Az érték.
     */
    protected void loadMatrix(int location, Matrix4f matrix) {
        matrix.get(matrixBuffer);
        GL20.glUniformMatrix4fv(location, false, matrixBuffer);
    }
}
