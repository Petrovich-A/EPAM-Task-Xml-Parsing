package by.petrovich.computer;

import by.petrovich.computer.exception.DeviceException;
import by.petrovich.computer.parser.DeviceSaxBuilder;
import by.petrovich.computer.validator.XmlValidator;

/**
 * Runner
 */
public class Runner {
    public static void main(String[] args) {
        XmlValidator xmlValidator = new XmlValidator();
        DeviceSaxBuilder deviceSaxBuilder = new DeviceSaxBuilder();
        xmlValidator.isFileValid("src/main/resources/files/computers.xml","src/main/resources/files/computers.xsd");
        try {
            deviceSaxBuilder.devicesBuilder("src/main/resources/files/computers.xml");
        } catch (DeviceException e) {
            e.printStackTrace();
        }


    }
}
