package hu.inf.unideb.td.model.SessionManagement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Egy hullám.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Wave {
    /**
     * A hullám komponenseit tartalmazó lista.
     */
    @XmlElement(name="WaveComponent")
    List<WaveComponent> waveComponents = new ArrayList<WaveComponent>();
    /**
     * A komponenseit tartalmazó lista lekérését végrehajtó metódus.
     * @return A lekért lista.
     */
    public List<WaveComponent> getWaveComponents() {
        return waveComponents;
    }

    /**
     * A hullám komponenseinek beállitására szolgáló metódus.
     * @param waveComponents A beállitani kivánt hullámkomponensek.
     */
    public void setWaveComponents(List<WaveComponent> waveComponents) {
        this.waveComponents = waveComponents;
    }
}
