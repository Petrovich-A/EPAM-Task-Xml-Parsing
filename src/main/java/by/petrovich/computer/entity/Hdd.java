package by.petrovich.computer.entity;

import by.petrovich.computer.entity.type.Country;
import by.petrovich.computer.entity.type.Peripheral;

import java.time.LocalDate;

public class Hdd extends DeviceAbstract {
    private int volume;

    public Hdd() {
    }

    public Hdd(int volume) {
        this.volume = volume;
    }

    public Hdd(String deviceId, String picture, String name, LocalDate deliveryDate, Country country, double price, Peripheral peripheral, String port, boolean critical, int volume) {
        super(deviceId, picture, name, deliveryDate, country, price, peripheral, port, critical);
        this.volume = volume;
    }

    public int volume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hdd)) return false;
        if (!super.equals(o)) return false;

        Hdd hdd = (Hdd) o;

        return volume == hdd.volume;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + volume;
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Hdd{");
        sb.append(super.toString());
        sb.append("volume=").append(volume);
        sb.append('}');
        return sb.toString();
    }
}
