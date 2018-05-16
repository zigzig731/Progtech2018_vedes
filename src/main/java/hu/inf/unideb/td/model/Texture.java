package hu.inf.unideb.td.model;

/**
 * Egy textúra tárolására szolgáló osztály.
 */
public class Texture {
    /**
     * A betöltött textúra opengl által kapott ID-je
     */
    private int textureID;

    /**
     * A textúra konstruktora.
     * @param id A létrehozni kivánt textúra ID-je.
     */
    public Texture(int id) {
        this.textureID = id;
    }

    /**
     * Ezzel a metódussal lekérhetjük egy textúra ID-jét.
     * @return A textúra ID-je.
     */
    public int getTextureID() {
        return textureID;
    }
}
