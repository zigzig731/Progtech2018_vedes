package hu.inf.unideb.td.model.SessionManagement;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class WaveComponent {
    @XmlElement(name="type")
    private int type;
    @XmlElement(name="amount")
    private int amount;
    @XmlElement(name="timeInterval")
    private float timeInterval;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(float timeInterval) {
        this.timeInterval = timeInterval;
    }
}
