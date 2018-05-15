package hu.inf.unideb.td.model;

import java.util.ArrayList;

/**
 * Egy modell tárolására szolgáló osztály.
 */
public class Model {

    /**
     * A VAO-jának modell opengl által adott ID-ja.
     */
    private int vao;
    /**
     * A modell térpontszáma.
     */
    private int vertexCount;

    /**
     * A modell konstruktora.
     * @param vao A VAO ID-je amiben a modell tárolva van.
     * @param vertexCount A modell térpontszáma.
     */
    public Model(int vao, int vertexCount) {
        this.vao = vao;
        this.vertexCount = vertexCount;
    }

    /**
     * A modell VAO ID-jét tudjuk vele lekérni.
     * @return A modell VAO ID-je.
     */
    public int getVao() {
        return vao;
    }

    /**
     * A modell térpontszámát tudjuk vele lekérni.
     * @return A modell térpontszáma.
     */
    public int getVertexcount() {
        return vertexCount;
    }

}
