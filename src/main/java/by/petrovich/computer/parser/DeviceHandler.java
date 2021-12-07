package by.petrovich.computer.parser;

import by.petrovich.computer.entity.Cpu;
import by.petrovich.computer.entity.DeviceAbstract;
import by.petrovich.computer.entity.DeviceTag;
import by.petrovich.computer.entity.Hdd;
import by.petrovich.computer.entity.type.Country;
import by.petrovich.computer.entity.type.DeviceType;
import by.petrovich.computer.entity.type.Peripheral;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DeviceHandler extends DefaultHandler {
    private DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
    private static final char HYPHEN = '-';
    private static final char UNDERSCORE = '_';
    private static final char SPACE = ' ';
    private static final Logger logger = LogManager.getLogger();
    private final List<DeviceAbstract> devices;
    private DeviceAbstract currentDevice;
    private DeviceTag currentTag;

    public DeviceHandler() {
        devices = new ArrayList<>();
    }

    public List<DeviceAbstract> getDevices() {
        return devices;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        logger.info("Parsing has began");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        String name = qName.toUpperCase(Locale.ROOT).replace(HYPHEN, UNDERSCORE);
        if (qName.equals(DeviceType.CPU.toString()) || qName.equals(DeviceType.HDD.toString())) {
            DeviceType deviceType = DeviceType.valueOf(name);
            switch (deviceType) {
                case CPU:
                    currentDevice = new Cpu();
                    break;
                case HDD:
                    currentDevice = new Hdd();
                    break;
                default:
                    currentDevice = null;
                    throw new RuntimeException();
            }
            if (attributes.getLength() == 1) {
                currentDevice.setDeviceId(attributes.getValue(0));
                logger.info("startElement: DeviceId: " + attributes.getValue(0));
                currentDevice.setDeviceId(DeviceAbstract.DEFAULT_PICTURE);
            } else {
                int idAttributeIndex = attributes.getLocalName(0).equals(DeviceTag.ID.toString()) ? 0 : 1;
                int pictureAttributeIndex = 1 - idAttributeIndex;
                currentDevice.setDeviceId(attributes.getValue(idAttributeIndex));
                currentDevice.setPicture(attributes.getValue(pictureAttributeIndex));
            }
        } else {
            DeviceTag deviceTag = DeviceTag.valueOf(name);
            currentTag = deviceTag;
        }
        logger.info("DeviceHandler: " + name.trim());
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals(DeviceType.CPU.toString()) || qName.equals(DeviceType.HDD.toString())) {
            devices.add(currentDevice);
            currentDevice = null;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        logger.info("Parsing has ended");
        System.out.println("\nParsing has ended");
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).trim();
        if (currentTag != null && !data.isEmpty()) {
            switch (currentTag) {
                case NAME:
                    currentDevice.setName(data);
                    logger.info("characters: " + data);
                    break;
                case DELIVERY_DATE:
                    String deliveryDate = data.toUpperCase(Locale.ROOT);
                    currentDevice.setDeliveryDate(LocalDate.parse(deliveryDate, DATE_FORMATTER));
                    logger.info("characters: " + data);
                    break;
                case COUNTRY:
                    String country = data.toUpperCase(Locale.ROOT);
                    currentDevice.setCountry(Country.valueOf(country));
                    logger.info("characters: " + data);
                    break;
                case PRICE:
                    String price = data.toUpperCase(Locale.ROOT);
                    currentDevice.setPrice(Double.parseDouble(price));
                    logger.info("characters: " + data);
                    break;
                case PERIPHERAL:
                    String peripheral = data.toUpperCase(Locale.ROOT).replace(SPACE, UNDERSCORE);
                    currentDevice.setPeripheral(Peripheral.valueOf(peripheral));
                    logger.info("characters: " + data);
                    break;
                case PORT:
                    String port = data.toUpperCase(Locale.ROOT);
                    currentDevice.setPort(port);
                    logger.info("characters: " + data);
                    break;
                case CRITICAL:
                    String critical = data.toUpperCase(Locale.ROOT);
                    currentDevice.setCritical(Boolean.parseBoolean(critical));
                    logger.info("characters: " + data);
                    break;
                case FREQUENCY:
                    Cpu cpu = (Cpu) currentDevice;
                    String frequency = data.toUpperCase(Locale.ROOT);
                    cpu.setFrequency(Integer.parseInt(frequency));
                    logger.info("characters: " + data);
                    break;
                case VOLUME:
                    Hdd hdd = (Hdd) currentDevice;
                    String volume = data.toUpperCase(Locale.ROOT);
                    hdd.setVolume(Integer.parseInt(volume));
                    logger.info("characters: " + data);
                    break;
                default:
                    throw new EnumConstantNotPresentException(currentTag.getDeclaringClass(), currentTag.name());
            }

        }
    }
}

