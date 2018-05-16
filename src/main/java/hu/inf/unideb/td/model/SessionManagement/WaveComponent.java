package hu.inf.unideb.td.model.SessionManagement;

import javax.xml.bind.annotation.*;

/**
 * Egy hullámkomponens.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class WaveComponent {
    /**
     * A komponens tipusa.
     */
    @XmlElement(name="type")
    private int type;
    /**
     * A komponens mennyisége.
     */
    @XmlElement(name="amount")
    private int amount;
    /**
     * A komponens érekzési időköze.
     */
    @XmlElement(name="timeInterval")
    private float timeInterval;

    /**
     * A komponens tipusának lekérdezésére szolgáló metódus.
     * @return A komponens tipusa
     */
    public int getType() {
        return type;
    }

    /**
     * A komponens mennyiségének lekérdezésére szolgáló metódus.
     * @return A komponens mennyisége
     */
    public int getAmount() {
        return amount;
    }

    /**
     * A komponens érkezési időközének lekérdezésére szolgáló metódus.
     * @return A komponens érkezési időköze.
     */
    public float getTimeInterval() {
        return timeInterval;
    }

}
