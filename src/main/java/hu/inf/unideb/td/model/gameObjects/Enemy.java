package hu.inf.unideb.td.model.gameObjects;

import hu.inf.unideb.td.model.*;
import hu.inf.unideb.td.model.mapElements.Path;
import hu.inf.unideb.td.model.player.Camera;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * Ez az osztály a GameObject osztályból származik.
 * Általános viselkedést implementál, mely minden ellenfélnek része.
 * @see hu.inf.unideb.td.model.GameObject
 */
public class Enemy extends GameObject {
    /**
     * Ez az ellenfél mozgási sebessége.
     * Minden ellenségtipusnál más értéket vehet fel.
     */
    private float speed = 3f;
    /**
     * A fittness azt mutatja hogy az ellenfél milyen messze jutott el az úton a pálya vége felé.
     */
    private float fittness;
    /**
     * Az ellenfelek waypointokat követnek az úton.
     * Ez a változó tárolja az éppen követett pontot.
     * Alapértelmezett értéke az út első pontja.
     * @see Path
     */
    private Vector3f target = Path.getWaypoint(0);
    /**
     * A targetIndex változó az éppen követett waypoint pathban lévő indexét tárolja.
     * Alapértelmezetten az értéke 0
     * @see Path
     */
    private int targetIndex = 0;
    /**
     * Ez a változó az ellenség maximum életét tárolja.
     * Minden ellenségtipusnál más értéket vehet fel.
     */
    private float maxHealth = 100;
    /**
     * A health változó az ellenség pillanatnyi életéterejét tárolja.
     * Alapból az értéke a maxHealth változó értékével egyenlő
     */
    private float health = maxHealth;
    /**
     * A healthBarMat változó az életerőcsik megjelenitéséhez szükséges MaterialInstance-t tárolja.
     * Szine az ellenfél pillanatnyi életerejétől függ, melyet a health változóban tárolunk.
     */
    private MaterialInstance healthBarMat;


    /**
     * Ez a metódus visszaadja egy enemy pillanatnyi életét.
     * @return Egy enemy életereje.
     */
    public float getHealth() {
        return health;
    }
    /**
     * Ez a metódus visszaadja egy enemy maximum lehetséges életét.
     * @return Egy enemy pillanatnyi életereje.
     */
    public float getMaxHealt() {
        return maxHealth;
    }
    /**
     * Ez a metódus visszaadja egy enemy mozgási sebességét.
     * @return Egy enemy maximálisan lehetséges életereje.
     */
    public float getSpeed() {
        return speed;
    }
    /**
     * Ez a metódus beállitja egy enemy mozgási sebességét.
     * @param speed A beállitani kivánt mozgási sebesség.
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    /**
     * Ez a metódus visszaadja egy enemy pillanatnyi fittnes értékét.
     * @return Egy enemy mozgási sebessége.
     */
    public float getFittness() {
        return fittness;
    }
    /**
     * Ez a metódus beállitja egy enemy pillanatnyi fittnes értékét.
     * @param fittness  A beállitani kivánt fittness érték.
     */
    public void setFittness(float fittness) {
        this.fittness = fittness;
    }

    /**
     * Az Enemy osztály konstruktora.
     * létrehozza az Enemy GameObjectet alkotó Entityket.
     */
    public Enemy() {
        healthBarMat = new MaterialInstance(new Vector3f(0, 1, 0));
        entities.add(new Entity(healthBarMat, "healthbar"));
        entities.get(0).setLocalPosition(new Vector3f(0, 3, 0));
        position = new Vector3f(6, 0, 4);
        entities.get(0).setScale(0.2f);
    }
    /**
     * Az Enemy osztály tesztekhez használt konstruktora, a grafikai elemek nélkül.
     * @param test Ez csak egy eldobható paraméter, hogy megkülönböztessem a sima konstruktortól.
     */
    public Enemy(boolean test) {
        healthBarMat = new MaterialInstance(true);
        entities.add(new Entity(true));
        entities.get(0).setLocalPosition(new Vector3f(0, 3, 0));
        position = new Vector3f(6, 0, 4);
        entities.get(0).setScale(0.2f);
        entities.add(new Entity(true));
        entities.get(1).setLocalRotation(new Vector3f(0, 180, 0));
    }


    /**
     * Mindig az éppen aktuálisan követett waypoint felé mozgatja az enemy-t, ha az enemy a target adott sugarú körébe ért, a target változó értékét beállitja a pathon lévő következő waypointra. Amennyiben az nem létezik törli az enemy-t.
     * @see Path
     */
    private void move() {
        Vector3f dir = new Vector3f(target);
        dir.sub(position);
        if (dir.length() < 0.4) {
            targetIndex++;
            if (targetIndex < Path.waypoints.size()) target = Path.getWaypoint(targetIndex);
            else destroy();
        }
        entities.get(1).setRotation(new Vector3f(0, 180 + (float) Math.toDegrees(new Vector2f(dir.x, dir.z).normalize().angle(new Vector2f(1, 0).normalize())), 0));
        dir.normalize(0.1f);
        dir.mul(speed);
        fittness += dir.length();
        position.add(dir);

    }

    /**
     * Az életerő csikot mindig úgy forgatja hogy az a kamera felé nézzen.
     * @see Camera
     */
    private void rotateHealthBar() {
        Vector3f lookAt = new Vector3f(position);

        lookAt.sub(Camera.position);
        float roty = (float) Math.toDegrees(new Vector2f(lookAt.x, lookAt.z).normalize().angle(new Vector2f(1, 0).normalize()));
        entities.get(0).setRotation(new Vector3f(0, roty, 45));
        healthBarMat.setBaseColor(new Vector3f(1 - (float) health / (float) maxHealth, (float) health / (float) maxHealth, 0));
    }

    /**
     * Az adott enemy életét csökkenti a bejövő sebzés értékével.
      * @param damage A bvejövő sebzés értéke.
     */
    public void dealDamage(float damage) {
        health -= damage;
    }


    @Override
    public void update() {
        if (health <= 0) destroy();
        move();
        rotateHealthBar();
    }

}
