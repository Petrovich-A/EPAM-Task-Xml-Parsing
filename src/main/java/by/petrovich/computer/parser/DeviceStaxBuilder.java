package by.petrovich.computer.parser;

import by.petrovich.computer.entity.DeviceAbstract;
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

    
}
