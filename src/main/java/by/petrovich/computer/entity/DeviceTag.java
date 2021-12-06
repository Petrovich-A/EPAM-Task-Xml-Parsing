package by.petrovich.computer.entity;

public enum DeviceTag {
    DEVICES,
    CPU,
    HDD,

    ID,
    PICTURE,
    NAME,
    DELIVERY_DATE,
    COUNTRY,
    PRICE,
    PROPERTY,
        PERIPHERAL,
        PORT,
    CRITICAL,

            FREQUENCY,
            VOLUME;

    private static final String UNDERSCORE = "_";
    private static final String HYPHEN = "-";

    @Override
    public String toString() {
        String result = this.name();
        result = result.toLowerCase();
        result = result.replace(UNDERSCORE, HYPHEN);
        return result;
    }
}
