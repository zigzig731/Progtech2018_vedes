package hu.inf.unideb.td.model;

import java.util.ArrayList;

public class Model {

    private int vao;
    private int vertexCount;

    public Model(int vao, int vertexCount) {
        this.vao = vao;
        this.vertexCount = vertexCount;
    }

    public int getVao() {
        return vao;
    }

    public int getVertexcount() {
        return vertexCount;
    }

}
