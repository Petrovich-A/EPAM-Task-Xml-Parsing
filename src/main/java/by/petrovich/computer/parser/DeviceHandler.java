package by.petrovich.computer.parser;

import by.petrovich.computer.entity.DeviceAbstract;
import by.petrovich.computer.entity.DeviceTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class DeviceHandler extends DefaultHandler {
    private static final Logger logger = LogManager.getLogger();
    private final List<DeviceAbstract> devicesAbstracts;
    private DeviceTag currentDevice;
    private EnumSet<DeviceTag> withText;

    public DeviceHandler() {
        devicesAbstracts = new ArrayList<>();
        withText = EnumSet.range(DeviceTag.NAME, DeviceTag.DELIVERY_DATE);
    }

    public List<DeviceAbstract> getDevicesAbstracts() {
        return devicesAbstracts;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        logger.info("Parsing has began");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String name = qName;
        for (int i = 0; i < attributes.getLength(); i++) {
            name += " " + attributes.getLocalName(i) + " = " + attributes.getValue(i);
        }
        logger.info("startElement" + name.trim());
        System.out.println(name.trim());
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println(localName);
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

