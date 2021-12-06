package by.petrovich.computer.entity;

import by.petrovich.computer.entity.type.Country;
import by.petrovich.computer.entity.type.Peripheral;

import java.time.LocalDate;

public class Cpu extends DeviceAbstract {
    private int sequanse;

    public Cpu() {
    }

    public Cpu(int sequanse) {
        this.sequanse = sequanse;
    }

    public Cpu(String deviceId, String picture, String name, LocalDate deliveryDate, Country country, double price, Peripheral peripheral, String port, boolean critical, int sequanse) {
        super(deviceId, picture, name, deliveryDate, country, price, peripheral, port, critical);
        this.sequanse = sequanse;
    }

    public int sequanse() {
        return sequanse;
    }

    public void setSequanse(int sequanse) {
        this.sequanse = sequanse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cpu)) return false;
        if (!super.equals(o)) return false;

        Cpu cpu = (Cpu) o;

        return sequanse == cpu.sequanse;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + sequanse;
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Cpu{");
        sb.append(super.toString());
        sb.append("sequanse=").append(sequanse);
        sb.append('}');
        return sb.toString();
    }
}
