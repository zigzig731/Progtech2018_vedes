package hu.inf.unideb.td.model;

import hu.inf.unideb.td.model.utility.Loader;
import org.joml.Vector3f;

/**
 * Materialinstance osztály a materialok variációinak létrehozására szolgál.
 */
public class MaterialInstance {
    /**
     * A materialinstance három alap textúrája a diffuse, specular és normal.
     */
    private Texture diffuse, specular, normal;
    /**
     * A materialinstance három alap textúrájának erőssége a diffuse, specular és normal intensity.
     */
    private float diffuseIntensity, specularIntensity, normalIntensity;
    /**
     * A textúra ismétlődésének mértéke.
     */
    private float uvTileing;
    /**
     * A materialinstance alap szine.
     */
    private Vector3f baseColor=new Vector3f(0,0,0);
    /**
     * Azt jelzi hogy a material animált-e.
     */
    public boolean animated=false;

    /**
     * Az alapszin lekérésére szolgáló metódus.
     * @return A materialinstance alapszine.
     */
    public Vector3f getBaseColor() {
        return baseColor;
    }

    /**
     * Az alapszin beállitására szolgáló metódus.
     * @param baseColor A beállitani kivánt alapszin.
     */
    public void setBaseColor(Vector3f baseColor) {
        this.baseColor = baseColor;
    }

    /**
     * A materialinstance egy konstruktora.
     * @param baseColor A beállitani kivánt alapszin.
     */
    public MaterialInstance(Vector3f baseColor) {
        Loader loader = new Loader();
        String textureName = "basecolor";
        this.diffuse = new Texture(loader.loadTexture("Textures/" + textureName + "/" + textureName + "_COL.png"));
        this.specular = new Texture(loader.loadTexture("Textures/" + textureName + "/" + textureName + "_REFL.png"));
        this.normal = new Texture(loader.loadTexture("Textures/" + textureName + "/" + textureName + "_NRM.png"));
        this.diffuseIntensity = 1;
        this.specularIntensity = 1;
        this.normalIntensity = 1;
        this.uvTileing = 1;
        this.baseColor = baseColor;
    }
    /**
     * A MaterialInstance osztály tesztekhez használt konstruktora, a grafikai elemek nélkül.
     * @param test Ez csak egy eldobható paraméter, hogy megkülönböztessem a sima konstruktortól.
     */
    public MaterialInstance(boolean test) {

    }

    /**
     * A materialinstance egy konstruktora.
     * @param mat A szülő material neve.
     * @param diffuseIntensity Az instance diffuse intenzitása.
     * @param specularIntensity Az instance specular intenzitása.
     * @param normalIntensity Az instance normal intenzitása.
     * @param uvTileing Az instance ismétlődése.
     */
    public MaterialInstance(Material mat, float diffuseIntensity, float specularIntensity, float normalIntensity, float uvTileing) {
        this.diffuse = mat.getDiffuse();
        this.specular = mat.getSpecular();
        this.normal = mat.getNormal();
        this.diffuseIntensity = diffuseIntensity;
        this.specularIntensity = specularIntensity;
        this.normalIntensity = normalIntensity;
        this.uvTileing = uvTileing;
    }
    /**
     * A material diffuse textúrájának getterje.
     * @return A materialinstance diffuse textúrája.
     */
    public Texture getDiffuse() {
        return diffuse;
    }

    /**
     * Ez a metódus egy materialinstance diffuse szinének beállitására szolgál.
     * @param diffuse A beállitani kivánt textúra.
     */
    public void setDiffuse(Texture diffuse) {
        this.diffuse = diffuse;
    }
    /**
     * A material diffuse textúrájának getterje.
     * @return A materialinstance specular textúrája.
     */
    public Texture getSpecular() {
        return specular;
    }
    /**
     * A material diffuse textúrájának getterje.
     * @return A materialinstance normal textúrája.
     */
    public Texture getNormal() {
        return normal;
    }


}

