package by.petrovich.computer.parser;

import by.petrovich.computer.entity.Cpu;
import by.petrovich.computer.entity.DeviceAbstract;
import by.petrovich.computer.entity.DeviceTag;
import by.petrovich.computer.entity.Hdd;
import by.petrovich.computer.entity.type.Country;
import by.petrovich.computer.entity.type.DeviceType;
import by.petrovich.computer.entity.type.Peripheral;
import by.petrovich.computer.exception.DeviceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DeviceStaxBuilder implements DeviceBuilder {
    private DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
    private static final Logger logger = LogManager.getLogger();
    private static final char HYPHEN = '-';
    private static final char UNDERSCORE = '_';
    private static final char SPACE = ' ';
    private final List<DeviceAbstract> devices;
    private final XMLInputFactory xmlInputFactory;

    public DeviceStaxBuilder() {
        xmlInputFactory = XMLInputFactory.newFactory();
        devices = new ArrayList<>();
    }

    @Override
    public void devicesBuilder(String filePath) throws DeviceException {
        XMLStreamReader xmlStreamReader;
        String name;
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            xmlStreamReader = xmlInputFactory.createXMLStreamReader(fileInputStream);
            while (xmlStreamReader.hasNext()) {
                int type = xmlStreamReader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = xmlStreamReader.getLocalName();
                    if (name.equals(DeviceType.CPU.toString()) || name.equals(DeviceType.HDD.toString())) {
                        DeviceAbstract deviceAbstract = devicesBuild(xmlStreamReader);
                        devices.add(deviceAbstract);
                    }
                }
            }
        } catch (XMLStreamException | IOException e) {
            logger.log(Level.INFO, "DevicesBuilder exception", e);
        }
        logger.log(Level.INFO, "The devises are created");
    }

    @Override
    public List<DeviceAbstract> getDevices() {
        return List.copyOf(devices);
    }

    private DeviceAbstract devicesBuild(XMLStreamReader xmlStreamReader) throws XMLStreamException {
        DeviceAbstract deviceAbstract = null;
        DeviceType deviceType = DeviceType.valueOf(xmlStreamReader.getLocalName().toUpperCase(Locale.ROOT));
        switch (deviceType) {
            case CPU:
                deviceAbstract = new Cpu();
                break;
            case HDD:
                deviceAbstract = new Hdd();
                break;
        }
        String id = xmlStreamReader.getAttributeValue(null, DeviceTag.ID.toString());
        deviceAbstract.setDeviceId(id);
        String picture = xmlStreamReader.getAttributeValue(null, DeviceTag.PICTURE.toString());
        if (picture == null) {
            picture = DeviceAbstract.DEFAULT_PICTURE;
        }
        deviceAbstract.setPicture(picture);
        String name;
        while (xmlStreamReader.hasNext()) {
            int type = xmlStreamReader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT: {
                    name = xmlStreamReader.getLocalName().toUpperCase(Locale.ROOT).replace(HYPHEN, UNDERSCORE);
                    setDeviceProperty(xmlStreamReader, name, deviceAbstract);
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    name = xmlStreamReader.getLocalName().toUpperCase(Locale.ROOT).replace(HYPHEN, UNDERSCORE);
                    if (DeviceTag.valueOf(name) == DeviceTag.CPU
                            || DeviceTag.valueOf(name) == DeviceTag.HDD) {
                        return deviceAbstract;
                    }
                    break;
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag");
    }

    private void setDeviceProperty(XMLStreamReader xmlStreamReader, String name, DeviceAbstract deviceAbstract) throws
            XMLStreamException {
        switch (DeviceTag.valueOf(name)) {
            case NAME:
                String deviceName = getXmlText(xmlStreamReader);
                deviceAbstract.setName(deviceName);
                logger.info("setDeviceProperty: " + deviceName);
                break;
            case DELIVERY_DATE:
                String deliveryDate = getXmlText(xmlStreamReader);
                deviceAbstract.setDeliveryDate(LocalDate.parse(deliveryDate, DATE_FORMATTER));
                logger.info("setDeviceProperty: " + deliveryDate);
                break;
            case COUNTRY:
                String country = getXmlText(xmlStreamReader);
                deviceAbstract.setCountry(Country.valueOf(country.toUpperCase(Locale.ROOT)));
                logger.info("setDeviceProperty: " + country);
                break;
            case PRICE:
                String price = getXmlText(xmlStreamReader);
                deviceAbstract.setPrice(Double.parseDouble(price));
                logger.info("setDeviceProperty: " + price);
                break;
            case PERIPHERAL:
                String peripheral = getXmlText(xmlStreamReader);
                deviceAbstract.setPeripheral(Peripheral.valueOf(peripheral.toUpperCase(Locale.ROOT).replace(SPACE, UNDERSCORE)));
                logger.info("setDeviceProperty: " + peripheral);
                break;
            case PORT:
                String port = getXmlText(xmlStreamReader);
                deviceAbstract.setPort(port);
                logger.info("setDeviceProperty: " + port);
                break;
            case CRITICAL:
                String critical = getXmlText(xmlStreamReader);
                deviceAbstract.setCritical(Boolean.parseBoolean(critical));
                logger.info("setDeviceProperty: " + critical);
                break;
            case FREQUENCY:
                Cpu cpu = (Cpu) deviceAbstract;
                String frequency = getXmlText(xmlStreamReader);
                cpu.setFrequency(Integer.parseInt(frequency));
                logger.info("setDeviceProperty: " + frequency);
                break;
            case VOLUME:
                Hdd hdd = (Hdd) deviceAbstract;
                String volume = getXmlText(xmlStreamReader);
                hdd.setVolume(Integer.parseInt(volume));
                logger.info("setDeviceProperty: " + volume);
                break;
        }
    }

    private String getXmlText(XMLStreamReader xmlStreamReader) throws XMLStreamException {
        String text = null;
        if (xmlStreamReader.hasNext()) {
            xmlStreamReader.next();
            text = xmlStreamReader.getText();
        }
        return text;
    }

}
