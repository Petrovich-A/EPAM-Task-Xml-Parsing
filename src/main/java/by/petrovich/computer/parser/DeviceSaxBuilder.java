package by.petrovich.computer.parser;

import by.petrovich.computer.entity.Device;
import by.petrovich.computer.exception.DeviceException;
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
    private List<Device> devices;

    @Override
    public void deviceSaxBuilder(String filePath) throws DeviceException {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XMLReader reader = saxParser.getXMLReader();
            var handler = new DeviceHandler();
            reader.setContentHandler(handler);
            reader.parse(filePath);
//devices = handler
        } catch (ParserConfigurationException e) {
            throw new DeviceException("", e);
        } catch (EnumConstantNotPresentException | SAXException e) {
            throw new DeviceException("", e);
        } catch (IOException e) {
            throw new DeviceException("", e);
        }

    }

    @Override
    public List<Device> getDevices() {
        return devices;
    }
}
