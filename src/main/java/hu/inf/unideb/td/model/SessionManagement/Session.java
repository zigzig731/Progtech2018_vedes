package hu.inf.unideb.td.model.SessionManagement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * XML root elem.
 */
@XmlRootElement(name="Session")

/**
 * Egy játékmenet hullámait eltároló osztály.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Session {
    /**
     * Egy játékmenetben lévő hullámok listája.
     */
    @XmlElement(name="Wave")
    private List<Wave> waves = new ArrayList<Wave>();

    /**
     * A játékmenetben lévő hullámok lekérésére alkalmas metódus.
     * @return A játékmenetbenlévő hullámok listája.
     */
    public List<Wave> getWaves() {
        return waves;
    }

    /**
     * A játékmenetben lévő hullámok beállitására szolgáló függvény.
     * @param waves A beállitandó hullámok listája.
     */
    public void setWaves(List<Wave> waves) {
        this.waves = waves;
    }
}
