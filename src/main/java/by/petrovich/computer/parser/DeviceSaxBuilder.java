package by.petrovich.computer.parser;

import by.petrovich.computer.entity.DeviceAbstract;
import by.petrovich.computer.exception.DeviceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class DeviceSaxBuilder implements DeviceBuilder {
    private static final Logger logger = LogManager.getLogger();
    private List<DeviceAbstract> devices;

    public DeviceSaxBuilder() {
    }

    @Override
    public void parseDevices(String filePath) throws DeviceException {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XMLReader reader = saxParser.getXMLReader();
            var handler = new DeviceHandler();
            reader.setContentHandler(handler);
            reader.parse(filePath);
            devices = handler.getDevices();
        } catch (ParserConfigurationException e) {
            throw new DeviceException("The file can't be red", e);
        } catch (EnumConstantNotPresentException | SAXException e) {
            throw new DeviceException("The file can't be parsed", e);
        } catch (IOException e) {
            throw new DeviceException("", e);
        }
        logger.log(Level.INFO, "The devices are created");
    }

    @Override
    public List<DeviceAbstract> getDevices() {
        return List.copyOf(devices);
    }
}
