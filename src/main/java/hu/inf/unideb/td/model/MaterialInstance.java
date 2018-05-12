package hu.inf.unideb.td.model;

import hu.inf.unideb.td.model.utility.Loader;
import org.joml.Vector3f;

public class MaterialInstance {
    private Texture diffuse, specular, normal;
    private float diffuseIntensity, specularIntensity, normalIntensity;
    private float uvTileing;
    private Vector3f baseColor = new Vector3f(0, 0, 0);
    public boolean animated=false;

    public Vector3f getBaseColor() {
        return baseColor;
    }

    public void setBaseColor(Vector3f baseColor) {
        this.baseColor = baseColor;
    }

    public MaterialInstance(Vector3f baseColor) {
        Loader loader = new Loader();
        String textureName = "basecolor";
        this.diffuse = new Texture(loader.loadTexture("src/main/resources/Textures/" + textureName + "/" + textureName + "_COL.png"));
        this.specular = new Texture(loader.loadTexture("src/main/resources/Textures/" + textureName + "/" + textureName + "_REFL.png"));
        this.normal = new Texture(loader.loadTexture("src/main/resources/Textures/" + textureName + "/" + textureName + "_NRM.png"));
        this.diffuseIntensity = 1;
        this.specularIntensity = 1;
        this.normalIntensity = 1;
        this.uvTileing = 1;
        this.baseColor = baseColor;
    }

    public MaterialInstance(Material mat, float diffuseIntensity, float specularIntensity, float normalIntensity, float uvTileing) {
        this.diffuse = mat.getDiffuse();
        this.specular = mat.getSpecular();
        this.normal = mat.getNormal();
        this.diffuseIntensity = diffuseIntensity;
        this.specularIntensity = specularIntensity;
        this.normalIntensity = normalIntensity;
        this.uvTileing = uvTileing;
    }

    public Texture getDiffuse() {
        return diffuse;
    }

    public void setDiffuse(Texture diffuse) {
        this.diffuse = diffuse;
    }

    public Texture getSpecular() {
        return specular;
    }

    public void setSpecular(Texture specular) {
        this.specular = specular;
    }

    public Texture getNormal() {
        return normal;
    }

    public void setNormal(Texture normal) {
        this.normal = normal;
    }

    public float getDiffuseIntensity() {
        return diffuseIntensity;
    }

    public void setDiffuseIntensity(float diffuseIntensity) {
        this.diffuseIntensity = diffuseIntensity;
    }

    public float getSpecularIntensity() {
        return specularIntensity;
    }

    public void setSpecularIntensity(float specularIntensity) {
        this.specularIntensity = specularIntensity;
    }

    public float getNormalIntensity() {
        return normalIntensity;
    }

    public void setNormalIntensity(float normalIntensity) {
        this.normalIntensity = normalIntensity;
    }

    public float getUvTileing() {
        return uvTileing;
    }

    public void setUvTileing(float uvTileing) {
        this.uvTileing = uvTileing;
    }
}

