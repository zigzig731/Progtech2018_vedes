package hu.inf.unideb.td.model;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.List;

import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.opengl.GL20.glUniform1i;

public class StaticShader extends ShaderProgram{
    private static final String VERTEX_FILE="res/Shaders/vertexshader.txt";
    private static final String FRAGMENT_FILE="res/Shaders/fragmentshader.txt";

    private static final int MAXLIGHTS = 5;

    private int locationttransformmatrix;
    private int locationprojectionmatrix;
    private int locationviewmatrix;
    private int locationlightposition[];
    private int locationlightcolor[];
    private int locationreflectivity;
    private int locationtime;
    private int locationattenuation[];
    private int locationbasecolor;

    public StaticShader()
    {
        super(VERTEX_FILE,FRAGMENT_FILE);

    }

    @Override
    protected void getAllUniformLocations() {
        locationttransformmatrix=super.getuniformlocation("transformationmatrix");
        locationprojectionmatrix=super.getuniformlocation("projectionmatrix");
        locationviewmatrix=super.getuniformlocation("viewmatrix");
        locationtime=super.getuniformlocation("time");
        locationreflectivity=super.getuniformlocation("reflectivity");
        locationbasecolor=super.getuniformlocation("basecolor");
        locationlightposition=new int[MAXLIGHTS];
        locationlightcolor=new int[MAXLIGHTS];
        locationattenuation=new int[MAXLIGHTS];
        for(int i=0;i<MAXLIGHTS;i++){
            locationlightposition[i] = super.getuniformlocation("lightposition["+i+"]");
            locationlightcolor[i] = super.getuniformlocation("lightcolor["+i+"]");
            locationattenuation[i] = super.getuniformlocation("attenuation["+i+"]");
        }
    }

    public void loadTexture()
    {
        glUniform1i(super.getuniformlocation("textureSampler"),0);
        glUniform1i(super.getuniformlocation("textureSamplerS"),1);
        glUniform1i(super.getuniformlocation("textureSamplerN"),2);

    }

    protected  void bindAttributes(){
        super.bindAttributes(0,"position");
        super.bindAttributes(1,"texturecoords");
        super.bindAttributes(2,"normal");
        super.bindAttributes(3,"tangent");
    }

    public void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix(locationttransformmatrix,matrix);
    }
    public void loadprojectionmatrix(Matrix4f projection)
    {
        super.loadMatrix(locationprojectionmatrix,projection);
    }
    public void loadViewMatrix(Camera camera)
    {
        Matrix4f viewmatrix= Maths.createViewMatrix(camera);
        super.loadMatrix(locationviewmatrix,viewmatrix);
    }

    public void loadLights(List<Light> lights)
    {
        for(int i=0;i<MAXLIGHTS;i++)
        {
            if(i<lights.size())
            {
                super.loadVector(locationlightposition[i],lights.get(i).getPosition());
                super.loadVector(locationlightcolor[i],lights.get(i).getColor());
                super.loadVector(locationattenuation[i],lights.get(i).getAttenuation());
            }else{
                super.loadVector(locationlightposition[i],new Vector3f(0,0,0));
                super.loadVector(locationlightcolor[i],new Vector3f(0,0,0));
                super.loadVector(locationattenuation[i],new Vector3f(1,1,0));
            }
        }
    }

    public void loadmaterial(MaterialInstance mat){
        if(mat.animated){
            super.loadFloat(locationtime,(float)glfwGetTime());
        }
        else super.loadFloat(locationtime,0);
        super.loadVector(locationbasecolor,mat.getBaseColor());

    }

}
