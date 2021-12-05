package by.petrovich.computer;

import by.petrovich.computer.exception.DeviceException;
import by.petrovich.computer.parser.DeviceHandler;
import by.petrovich.computer.parser.DeviceSaxBuilder;
import by.petrovich.computer.validator.XmlValidator;

/**
 * Runner
 */
public class Runner {
    public static void main(String[] args) {
        XmlValidator xmlValidator = new XmlValidator();
        System.out.println("xmlValidator: " + xmlValidator.isFileValid("src/main/resources/files/computers.xml", "src/main/resources/files/computers.xsd"));
        DeviceSaxBuilder deviceSaxBuilder = new DeviceSaxBuilder();
        try {
            deviceSaxBuilder.deviceSaxBuilder("src/main/resources/files/computers.xml");
        } catch (DeviceException e) {
            e.printStackTrace();
        }

        DeviceHandler deviceHandler = new DeviceHandler();



    }
}
