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
        if (!(o instanceof DeviceAbstract)) return false;

        DeviceAbstract that = (DeviceAbstract) o;

        if (Double.compare(that.getPrice(), getPrice()) != 0) return false;
        if (isCritical() != that.isCritical()) return false;
        if (getDeviceId() != null ? !getDeviceId().equals(that.getDeviceId()) : that.getDeviceId() != null)
            return false;
        if (getPicture() != null ? !getPicture().equals(that.getPicture()) : that.getPicture() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getDeliveryDate() != null ? !getDeliveryDate().equals(that.getDeliveryDate()) : that.getDeliveryDate() != null)
            return false;
        if (getCountry() != that.getCountry()) return false;
        if (getPeripheral() != that.getPeripheral()) return false;
        return getPort() != null ? getPort().equals(that.getPort()) : that.getPort() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getDeviceId() != null ? getDeviceId().hashCode() : 0;
        result = 31 * result + (getPicture() != null ? getPicture().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDeliveryDate() != null ? getDeliveryDate().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getPeripheral() != null ? getPeripheral().hashCode() : 0);
        result = 31 * result + (getPort() != null ? getPort().hashCode() : 0);
        result = 31 * result + (isCritical() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Device{");
        sb.append("deviceId='").append(deviceId).append('\'');
        sb.append(", picture='").append(picture).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", deliveryDate='").append(deliveryDate).append('\'');
        sb.append(", country=").append(country);
        sb.append(", price='").append(price).append('\'');
        sb.append(", peripheral=").append(peripheral);
        sb.append(", port='").append(port).append('\'');
        sb.append(", critical=").append(critical);
        sb.append('}');
        return sb.toString();
    }
}
