package by.petrovich.computer.parser;

import by.petrovich.computer.entity.Cpu;
import by.petrovich.computer.entity.DeviceAbstract;
import by.petrovich.computer.entity.DeviceTag;
import by.petrovich.computer.entity.Hdd;
import by.petrovich.computer.entity.type.DeviceType;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DeviceStaxBuilder implements DeviceBuilder {
    private static final Logger logger = LogManager.getLogger();
    private static final char HYPHEN = '-';
    private static final char UNDERSCORE = '_';
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
                        DeviceAbstract deviceAbstract = devicesBuilder(xmlStreamReader);
                        devices.add(deviceAbstract);

                    }
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.log(Level.INFO, "The devises are created");
    }

    @Override
    public List<DeviceAbstract> getDevices() {
        return devices;
    }

    private DeviceAbstract devicesBuild(XMLStreamReader xmlStreamReader) throws XMLStreamException {
        DeviceAbstract deviceAbstract;
        DeviceType deviceType = DeviceType.valueOf(xmlStreamReader.getLocalName().toUpperCase(Locale.ROOT));
        deviceAbstract = switch (deviceType) {
            case CPU:
                deviceAbstract = new Cpu();
                break;
            case HDD:
                deviceAbstract = new Hdd();
                break;
            default:
                deviceAbstract = null;
                throw new RuntimeException();
        };
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
                    if (DeviceTag.valueOf(name) == DeviceTag.CPU || DeviceTag.valueOf(name) == DeviceTag.HDD) {
                        return deviceAbstract;
                    }
                }

            }
        }
    }
}
