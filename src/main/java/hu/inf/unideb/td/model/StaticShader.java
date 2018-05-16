package hu.inf.unideb.td.model;

import hu.inf.unideb.td.model.player.Camera;
import hu.inf.unideb.td.model.utility.Maths;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.List;

import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.opengl.GL20.glUniform1i;

/**
 * Egy shader konkrét megvalósitása. Az alap shaderprogramból származi,
 */
public class StaticShader extends ShaderProgram{
    /**
     * A shader vertexshaderének elérési útja.
     */
    private static final String VERTEX_FILE="Shaders/vertexshader.txt";
    /**
     * A shader fragmentshaderének elérési útja.
     */
    private static final String FRAGMENT_FILE="Shaders/fragmentshader.txt";

    /**
     * A shaderben maximálisah használható fények számát jelzi.
     */
    private static final int MAXLIGHTS = 5;

    /**
     * Egy uniform változó helye a shaderben.
     */
    private int locationttransformmatrix;
    /**
     * Egy uniform változó helye a shaderben.
     */
    private int locationprojectionmatrix;
    /**
     * Egy uniform változó helye a shaderben.
     */
    private int locationviewmatrix;
    /**
     * Egy uniform változó helye a shaderben.
     */
    private int locationlightposition[];
    /**
     * Egy uniform változó helye a shaderben.
     */
    private int locationlightcolor[];
    /**
     * Egy uniform változó helye a shaderben.
     */
    private int locationreflectivity;
    /**
     * Egy uniform változó helye a shaderben.
     */
    private int locationtime;
    /**
     * Egy uniform változó helye a shaderben.
     */
    private int locationattenuation[];
    /**
     * Egy uniform változó helye a shaderben.
     */
    private int locationbasecolor;

    /**
     * A shader egy konstruktora.
     */
    public StaticShader()
    {
        super(VERTEX_FILE,FRAGMENT_FILE);

    }

    /**
     * Megkeresi az adott változók helyét a shaderben.
     */
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

    /**
     * Betölti a textúrákat a shaderbe.
     */
    public void loadTexture()
    {
        glUniform1i(super.getuniformlocation("textureSampler"),0);
        glUniform1i(super.getuniformlocation("textureSamplerS"),1);
        glUniform1i(super.getuniformlocation("textureSamplerN"),2);

    }

    /**
     * Az attribútumok csatolását végzi a shaderhez.
     */
    protected  void bindAttributes(){
        super.bindAttributes(0,"position");
        super.bindAttributes(1,"texturecoords");
        super.bindAttributes(2,"normal");
        super.bindAttributes(3,"tangent");
    }

    /**
     * A transformationMatrix shaderbe való töltéséért felelős.
     * @param matrix A betöltendő mátrix.
     */
    public void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix(locationttransformmatrix,matrix);
    }
    /**
     * A projection mátrix shaderbe való töltéséért felelős.
     * @param projection A betöltendő vetitési mátrix.
     */
    public void loadprojectionmatrix(Matrix4f projection)
    {
        super.loadMatrix(locationprojectionmatrix,projection);
    }

    /**
     * A viewMatrix shaderbe való töltéséért felelős.
     * @param camera A kamera mely alapján a view mátrixot kiszámoljuk.
     * @see Maths
     */
    public void loadViewMatrix(Camera camera)
    {
        Matrix4f viewmatrix= Maths.createViewMatrix(camera);
        super.loadMatrix(locationviewmatrix,viewmatrix);
    }

    /**
     * A fények shaderbe való töltéséért felelős.
     * @param lights A betöltendő fények.
     */
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

    /**
     * Egy material shaderbe való betöltéséért felelős.
     * @param mat A betöltendő material.
     */
    public void loadmaterial(MaterialInstance mat){
        if(mat.animated){
            super.loadFloat(locationtime,(float)glfwGetTime());
        }
        else super.loadFloat(locationtime,0);
        super.loadVector(locationbasecolor,mat.getBaseColor());

    }

}
