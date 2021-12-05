package by.petrovich.computer.parser;

import by.petrovich.computer.entity.Cpu;
import by.petrovich.computer.entity.DeviceAbstract;
import by.petrovich.computer.entity.DeviceTag;
import by.petrovich.computer.entity.Hdd;
import by.petrovich.computer.entity.type.DeviceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;

public class DeviceHandler extends DefaultHandler {
    private static final char HYPHEN = '-';
    private static final char UNDERSCORE = '_';
    private static final Logger logger = LogManager.getLogger();
    private final List<DeviceAbstract> devices;
    private DeviceAbstract currentDevice;
    private DeviceTag currentTag;
    private EnumSet<DeviceTag> withText;

    public DeviceHandler() {
        devices = new ArrayList<>();
        withText = EnumSet.range(DeviceTag.NAME, DeviceTag.DELIVERY_DATE);
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
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String name = qName.toUpperCase(Locale.ROOT).replace(HYPHEN, UNDERSCORE);
        if (qName.equals(DeviceType.CPU.toString()) || qName.equals(DeviceType.CPU.toString())) {
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
                currentDevice.setDeviceId(DeviceAbstract.DEFAULT_PICTURE);
            } else {
                int idAttributeIndex = attributes.getLocalName(0).equals(DeviceTag.ID.toString()) ? 0 : 1;
                int pictureAttributeIndex = 1 - idAttributeIndex;
                currentDevice.setDeviceId(attributes.getValue(idAttributeIndex));
                currentDevice.setPicture(attributes.getValue(pictureAttributeIndex));
            }
        } else {
            DeviceTag deviceTag = DeviceTag.valueOf(name);
            if (withText.contains(deviceTag)) {
                currentTag = deviceTag;
            }
        }


        for (int i = 0; i < attributes.getLength(); i++) {
            name += " " + attributes.getLocalName(i) + " = " + attributes.getValue(i);
        }
        logger.info("startElement" + name.trim());
        System.out.println(name.trim());
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
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
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println(new String(ch, start, length));
    }
}

