package hu.inf.unideb.td.model.SessionManagement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="Session")

@XmlAccessorType(XmlAccessType.FIELD)
public class Session {
    @XmlElement(name="Wave")
    private List<Wave> waves = new ArrayList<Wave>();

    public List<Wave> getWaves() {
        return waves;
    }

    public void setWaves(List<Wave> waves) {
        this.waves = waves;
    }
}
