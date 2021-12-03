package by.petrovich.computer.entity.type;

public enum DeviceType {
    CPU,
    HDD;

    @Override
    public String toString() {
        String result = this.name();
        result = result.toLowerCase();
        return result;
    }
}
