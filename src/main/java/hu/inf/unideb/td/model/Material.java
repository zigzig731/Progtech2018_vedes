package hu.inf.unideb.td.model;

import hu.inf.unideb.td.model.utility.Loader;
import org.joml.Vector3f;

/**
 * Material osztály a modellek kinézetének meghatározásához tartalmaz alapvető adatokat.
 */
public class Material {
    /**
     * A material három alap textúrája a diffuse, specular és normal.
     */
    private Texture diffuse, specular, normal;
    /**
     * A material három alap textúrájának erőssége a diffuse, specular és normal intensity.
     */
    private float diffuseIntensity, specularIntensity, normalIntensity;
    /**
     * A textúra ismétlődésének mértéke.
     */
    private float uvTileing;

    /**
     * A material alap szine.
     */
    private Vector3f baseColor=new Vector3f(0,0,0);

    /**
     * A material egy konstruktora.
     * @param diffuse A diffuse textúra.
     * @param specular A specular textúra.
     * @param normal A normal textúra.
     * @param diffuseIntensity A diffuse textúra intenzitása.
     * @param specularIntensity A specular textúra intenzitása.
     * @param normalIntensity A normal textúra intenzitása.
     * @param uvTileing A material ismétlődésének mértéke.
     */
    public Material(Texture diffuse, Texture specular, Texture normal, float diffuseIntensity, float specularIntensity, float normalIntensity, float uvTileing) {
        this.diffuse = diffuse;
        this.specular = specular;
        this.normal = normal;
        this.diffuseIntensity = diffuseIntensity;
        this.specularIntensity = specularIntensity;
        this.normalIntensity = normalIntensity;
        this.uvTileing = uvTileing;
    }

    /**
     * A material egy konstruktora.
     * @param textureName Egy textúracsomag elnevezése.
     * @param diffuseIntensity A diffuse textúra intenzitása.
     * @param specularIntensity A specular textúra intenzitása.
     * @param normalIntensity A normal textúra intenzitása.
     * @param uvTileing A material ismétlődésének mértéke.
     */
    public Material(String textureName, float diffuseIntensity, float specularIntensity, float normalIntensity, float uvTileing) {
        Loader loader = new Loader();
        this.diffuse = new Texture(loader.loadTexture("Textures/"+textureName+"/"+textureName+"_COL.png"));
        this.specular = new Texture(loader.loadTexture("Textures/"+textureName+"/"+textureName+"_REFL.png"));
        this.normal = new Texture(loader.loadTexture("Textures/"+textureName+"/"+textureName+"_NRM.png"));
        this.diffuseIntensity = diffuseIntensity;
        this.specularIntensity = specularIntensity;
        this.normalIntensity = normalIntensity;
        this.uvTileing = uvTileing;
    }

    /**
     * A material egy konstruktora.
     * @param baseColor A material alapszine.
     */
    public Material(Vector3f baseColor) {
        Loader loader = new Loader();
        String textureName="basecolor";
        this.diffuse = new Texture(loader.loadTexture("Textures/"+textureName+"/"+textureName+"_COL.png"));
        this.specular = new Texture(loader.loadTexture("Textures/"+textureName+"/"+textureName+"_REFL.png"));
        this.normal = new Texture(loader.loadTexture("Textures/"+textureName+"/"+textureName+"_NRM.png"));
        this.diffuseIntensity = 1;
        this.specularIntensity = 1;
        this.normalIntensity = 1;
        this.uvTileing = 1;
        this.baseColor =baseColor;
    }

    /**
     * A material diffuse textúrájának getterje.
     * @return A material diffuse textúrája.
     */
    public Texture getDiffuse() {
        return diffuse;
    }

    /**
     * A material specular textúrájának getterje.
     * @return A material specular textúrája.
     */
    public Texture getSpecular() {
        return specular;
    }

    /**
     * A material normal textúrájának getterje.
     * @return A material normal textúrája.
     */
    public Texture getNormal() {
        return normal;
    }

}
