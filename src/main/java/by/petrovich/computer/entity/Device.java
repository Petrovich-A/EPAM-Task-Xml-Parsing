package by.petrovich.computer.entity;

import by.petrovich.computer.entity.type.Country;
import by.petrovich.computer.entity.type.Peripheral;

public abstract class Device {
    public static final String DEFAULT_PICTURE = "pic.png";
    private String deviceId;
    private String picture;
    private String name;
    private String deliveryDate;
    private Country country;
    private String price;
    private Peripheral peripheral;
    private String port;
    private boolean critical;

    protected Device() {
    }

    public Device(String deviceId, String picture, String name, String deliveryDate, Country country, String price, Peripheral peripheral, String port, boolean critical) {
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

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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
        if (!(o instanceof Device)) return false;

        Device device = (Device) o;

        if (isCritical() != device.isCritical()) return false;
        if (getDeviceId() != null ? !getDeviceId().equals(device.getDeviceId()) : device.getDeviceId() != null)
            return false;
        if (getPicture() != null ? !getPicture().equals(device.getPicture()) : device.getPicture() != null)
            return false;
        if (getName() != null ? !getName().equals(device.getName()) : device.getName() != null) return false;
        if (getDeliveryDate() != null ? !getDeliveryDate().equals(device.getDeliveryDate()) : device.getDeliveryDate() != null)
            return false;
        if (getCountry() != device.getCountry()) return false;
        if (getPrice() != null ? !getPrice().equals(device.getPrice()) : device.getPrice() != null) return false;
        if (getPeripheral() != device.getPeripheral()) return false;
        return getPort() != null ? getPort().equals(device.getPort()) : device.getPort() == null;
    }

    @Override
    public int hashCode() {
        int result = getDeviceId() != null ? getDeviceId().hashCode() : 0;
        result = 31 * result + (getPicture() != null ? getPicture().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDeliveryDate() != null ? getDeliveryDate().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
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
