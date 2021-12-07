package by.petrovich.computer.entity;

import by.petrovich.computer.entity.type.Country;
import by.petrovich.computer.entity.type.Peripheral;

import java.time.LocalDate;

public class Cpu extends DeviceAbstract {
    private int frequency;

    public Cpu() {
    }

    public Cpu(String deviceId, String picture, String name, LocalDate deliveryDate, Country country, double price, Peripheral peripheral, String port, boolean critical, int frequency) {
        super(deviceId, picture, name, deliveryDate, country, price, peripheral, port, critical);
        this.frequency = frequency;
    }

    public int frequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cpu)) return false;
        if (!super.equals(o)) return false;

        Cpu cpu = (Cpu) o;

        return frequency == cpu.frequency;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + frequency;
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Cpu{");
        sb.append(super.toString());
        sb.append("frequency=").append(frequency);
        sb.append('}');
        return sb.toString();
    }
}
