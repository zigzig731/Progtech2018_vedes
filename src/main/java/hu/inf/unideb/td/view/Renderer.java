package hu.inf.unideb.td.view;

import hu.inf.unideb.td.model.*;
import org.joml.Matrix4f;

import java.util.List;

import static hu.inf.unideb.td.model.Maths.createProjectionMatrix;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Renderer {

    private StaticShader shader;
    private Matrix4f projectionMatrix;

    public Renderer(StaticShader shader){
        this.shader=shader;
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        projectionMatrix=createProjectionMatrix();
        shader.start();
        shader.loadprojectionmatrix(projectionMatrix);
        shader.stop();
    }

    public Matrix4f getProjectionmatrix()
    {
        return projectionMatrix;
    }

    public void prepare(){
    }

    public void render(Entity entity)
    {
        prepareMaterial(entity.getMaterial(),entity.getModel().getVao());
        prepareinstance(entity);
        glDrawElements(GL_TRIANGLES,entity.getModel().getVertexcount(),GL_UNSIGNED_INT,0);
        unbindtexturemodel();
    }

    public void prepareMaterial(MaterialInstance mat, int vao){
        glBindVertexArray(vao);
        shader.loadmaterial(mat);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        glEnableVertexAttribArray(3);
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D,mat.getDiffuse().getTextureID());
        glActiveTexture(GL_TEXTURE1);
        glBindTexture(GL_TEXTURE_2D,mat.getSpecular().getTextureID());
        glActiveTexture(GL_TEXTURE2);
        glBindTexture(GL_TEXTURE_2D,mat.getNormal().getTextureID());
    }

    private void unbindtexturemodel()
    {
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glDisableVertexAttribArray(3);
        glBindVertexArray(0);
    }

    private void prepareinstance(Entity entity)
    {
        Matrix4f transform=Maths.createTransformationMatrix(entity.getPosition(), entity.getRotation().x,entity.getRotation().y,entity.getRotation().z,entity.getScale());
        shader.loadTransformationMatrix(transform);
    }
}
