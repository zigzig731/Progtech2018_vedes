package hu.inf.unideb.td.model;

import hu.inf.unideb.td.model.managers.GameObjectManager;
import hu.inf.unideb.td.view.Renderer;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

/**
 * A GameObject egy olyan osztály, melynek segitségével összetartozó és fényeket tárolhatunk és jelenithetünk meg. Ezeket az összetartozó komponenseket különböző viselkedésekkel is elláthatjuk. A játékban minden olyan objektum ami nem teljesen statikus -mint például maga a pálya - GameObjectként tároljuk el, és jelenitjük meg.
 */
public abstract class GameObject {
    /**
     * A Gameobject poziciója, és forgási helyzete.
     */
    public Vector3f position = new Vector3f(0, 0, 0), rotation = new Vector3f(0, 0, 0);
    /**
     * A GameObject mérete.
     */
    public float scale=1;
    /**
     * A GameObject-et alkotó entityk listája.
     */
    public List<Entity> entities = new ArrayList<Entity>();
    /**
     * A GameObject-et alkotó fények listája.
     */
    public List<Light> lights = new ArrayList<Light>();

    /**
     * A GameObject pozicióját kérhetjük le vele.
     * @return A GameObject poziciója.
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * A GameObject pozicióját állithatjuk be vele.
     * @param position A beállitani kivánt pozició.
     */
    public void setPosition(Vector3f position) {
        this.position = position;
    }

    /**
     * A GameObject default konstruktora.
     */
    public GameObject() {
    }

    /**
     * A gameobject egy konstruktora.
     * @param position A létrehozni kivánt gameobject poziciója.
     * @param rotation A létrehozni kivánt gameobject forgása.
     * @param scale A létrehozni kivánt gameobject mérete.
     */
    public GameObject(Vector3f position, Vector3f rotation, float scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    /**
     A gameobject egy konstruktora.
     * @param position A létrehozni kivánt gameobject poziciója.
     * @param rotation A létrehozni kivánt gameobject forgása.
     * @param scale A létrehozni kivánt gameobject mérete.
     * @param entities A létrehozni kivánt gameobject alapértelmezett entitásainak listája.
     * @param lights A létrehozni kivánt gameobject alapértelmezett fényeinek listája.
     */
    public GameObject(Vector3f position, Vector3f rotation, float scale, List<Entity> entities, List<Light> lights) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.entities = entities;
        this.lights = lights;
    }

    /**
     * Az update metódust minden aktiv gameobjecten meghivjuk minden képkockán.
     */
    public abstract void update();

    /**
     * A gameobjecten meghivva a display methodot, megjeleniti magát a renderer segitségével.
     * @param renderer A renderer amivel a megjelenitést végezzük.
     * @see Renderer
     */
    public void display(Renderer renderer)
    {

        for(Entity entity:entities)
        {
            entity.setPosition(new Vector3f(this.position).add(entity.getLocalPosition()));
          //  entity.setRotation(new Vector3f(this.rotation).add(entity.getLocalRotation()));
            renderer.render(entity);
        }
    }

    /**
     * A gameobject destroy methodja megjelöli törlésre önmagát a GameObjectManager-ben.
     * @see GameObjectManager
     */
    public void destroy()
    {
        GameObjectManager.addToRemoved(this);
    }
}
