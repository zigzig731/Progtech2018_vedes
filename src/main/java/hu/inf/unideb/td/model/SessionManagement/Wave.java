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

    public List<WaveComponent> getWaveComponents() {
        return waveComponents;
    }

    public void setWaveComponents(List<WaveComponent> waveComponents) {
        this.waveComponents = waveComponents;
    }
}
