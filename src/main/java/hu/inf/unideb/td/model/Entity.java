package hu.inf.unideb.td.model;

import hu.inf.unideb.td.model.managers.MaterialManager;
import hu.inf.unideb.td.model.managers.ModelManager;
import org.joml.Vector3f;

/**
 * Az entity egy olyan oszátály ami kontextust ad a modelleknek, és lehetővé teszi azok megjelenitését.
 */
public class Entity {
    /**
     * Az entity megjelenitéséhez használt material.
     * @see MaterialInstance
     */
    private MaterialInstance material;
    /**
     * A modell, mely köré az entityt létrehozzuk.
     */
    private Model model;
    /**
     * Az entity világban elfoglalt poziciója, és forgási helyzete.
     */
    private Vector3f position=new Vector3f(0, 0, 0), rotation=new Vector3f(0, 0, 0);
    /**
     * Az entity lokálisan elfoglalt poziciója, és forgási helyzete.
     * Akkor használomamikor GameObjectben van jelen az entity.
     * @see GameObject
     */
    private Vector3f localPosition=new Vector3f(0, 0, 0), localRotation=new Vector3f(0, 0, 0);
    /**
     * Az entity mérete.
     */
    private float scale=1;

    /**
     * Az entity lokális pozicióját tudjuk vele lekérni.
     * @return Az entitás lokális helyzete.
     */
    public Vector3f getLocalPosition() {
        return localPosition;
    }

    /**
     * Az entity lokális pozicióját tudjuk vele beállitani.
     * @param localPosition A beállitani kivánt pozició.
     */
    public void setLocalPosition(Vector3f localPosition) {
        this.localPosition = localPosition;
    }
    /**
     * Az entity lokális forgásszögét tudjuk vele beállitani.
     * @param localRotation A beállitani kivánt szög.
     */
    public void setLocalRotation(Vector3f localRotation) {
        this.localRotation = localRotation;
    }

    /**
     * Az entityben használt materialt tudjuk vele lekérni.
     * @return A lekért material.
     */
    public MaterialInstance getMaterial() {
        return material;
    }

    /**
     * Az entityben használt modellt tudjuk vele lekérni.
     * @return A lekért modell.
     */
    public Model getModel() {
        return model;
    }

    /**
     * Az entity modelljét tudjuk vele beállitani.
     * @param model A beállitani kivánt modell.
     */
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * Az entity pozicióját tudjuk vele lekérni.
     * @return Az entitás helyzete.
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * Az entity lokális forgását tudjuk vele lekérni.
     * @return Az entitás lokális forgásszöge.
     */
    public Vector3f getLocalRotation() {
        return localRotation;
    }

    /**
     * Az entity pozicióját tudjuk vele beállitani.
     * @param position Az entitynek beállitani kivánt pozició.
     */
    public void setPosition(Vector3f position) {
        this.position = position;
    }
    /**
     * Az entity forgását tudjuk vele lekérni.
     * @return Az entitás forgásszöge.
     */
    public Vector3f getRotation() {
        return rotation;
    }

    /**
     * Az entity szögét tudjuk vele beállitani.
     * @param rotation A beállitani kivánt szög.
     */
    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    /**
     * Az entity méretét tudjuk vele lekérni.
     * @return Az entity mérete.
     */
    public float getScale() {
        return scale;
    }

    /**
     * Az entity méretét tudjuk vele beállitani.
     * @param scale A beállitani kivánt méret.
     */
    public void setScale(float scale) {
        this.scale = scale;
    }

    /**
     * Az entity egy konstruktora.
     * @param material Az entity kivánt materialja.
     * @param model Az entity modellje.
     * @param position Az entity poziciója.
     * @param rotation Az entity forgása.
     * @param scale Az entity mérete.
     */
    public Entity(String material, String model, Vector3f position, Vector3f rotation, float scale) {
        this.material = MaterialManager.get(material);
        this.model = ModelManager.get(model);
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    /**
     * Az entity egy konstruktora.
     * @param material Az entity kivánt materialja.
     * @param model Az entity modellje.
     */
    public Entity(String material, String model) {
        this.material = MaterialManager.get(material);
        this.model = ModelManager.get(model);
    }

    /**
     * Az entity egy konstruktora.
     * @param mat Az entity kivánt materialja.
     * @param model Az entity modellje.
     */
    public Entity(MaterialInstance mat, String model) {
        this.material = mat;
        this.model = ModelManager.get(model);
    }

    /**
     * Az entity osztály tesztekhez használt konstruktora, a grafikai elemek nélkül.
     *
     * @param test Ez csak egy eldobható paraméter, hogy megkülönböztessem a sima konstruktortól.
     */
    public Entity(boolean test) {
        this.material = null;
        this.model = null;
    }

}
