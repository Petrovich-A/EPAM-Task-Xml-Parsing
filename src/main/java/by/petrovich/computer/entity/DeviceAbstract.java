package by.petrovich.computer.entity;

import by.petrovich.computer.entity.type.Country;
import by.petrovich.computer.entity.type.Peripheral;

import java.time.LocalDate;

public abstract class DeviceAbstract {
    public static final String DEFAULT_PICTURE = "pic.png";
    private String deviceId;
    private String picture;
    private String name;
    private LocalDate deliveryDate;
    private Country country;
    private double price;
    private Peripheral peripheral;
    private String port;
    private boolean critical;

    protected DeviceAbstract() {
    }

    public DeviceAbstract(String deviceId, String picture, String name, LocalDate deliveryDate, Country country, double price, Peripheral peripheral, String port, boolean critical) {
        this.deviceId = deviceId;
        this.picture = picture;
        this.name = name;
        this.deliveryDate = deliveryDate;
        this.country = country;
        this.price = price;
        this.peripheral = peripheral;
        this.port = port;
        this.critical = critical;
    }

    public static String getDefaultPicture() {
        return DEFAULT_PICTURE;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Peripheral getPeripheral() {
        return peripheral;
    }

    public void setPeripheral(Peripheral peripheral) {
        this.peripheral = peripheral;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceAbstract that = (DeviceAbstract) o;

        if (Double.compare(that.price, price) != 0) return false;
        if (critical != that.critical) return false;
        if (deviceId != null ? !deviceId.equals(that.deviceId) : that.deviceId != null) return false;
        if (picture != null ? !picture.equals(that.picture) : that.picture != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (deliveryDate != null ? !deliveryDate.equals(that.deliveryDate) : that.deliveryDate != null) return false;
        if (country != that.country) return false;
        if (peripheral != that.peripheral) return false;
        return port != null ? port.equals(that.port) : that.port == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = deviceId != null ? deviceId.hashCode() : 0;
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (deliveryDate != null ? deliveryDate.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (peripheral != null ? peripheral.hashCode() : 0);
        result = 31 * result + (port != null ? port.hashCode() : 0);
        result = 31 * result + (critical ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DeviceAbstract{");
        sb.append("deviceId='").append(deviceId).append('\'');
        sb.append(", picture='").append(picture).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", deliveryDate=").append(deliveryDate);
        sb.append(", country=").append(country);
        sb.append(", price=").append(price);
        sb.append(", peripheral=").append(peripheral);
        sb.append(", port='").append(port).append('\'');
        sb.append(", critical=").append(critical);
        sb.append('}');
        return sb.toString();
    }
}
