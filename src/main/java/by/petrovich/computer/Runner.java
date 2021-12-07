package by.petrovich.computer;

import by.petrovich.computer.exception.DeviceException;
import by.petrovich.computer.parser.DeviceSaxBuilder;
import by.petrovich.computer.parser.DeviceStaxBuilder;
import by.petrovich.computer.validator.XmlValidator;

/**
 * Runner
 */
public class Runner {
    public static void main(String[] args) {
        String xmlFilePath = "src/main/resources/files/computers.xml";
        String xsdFilePath = "src/main/resources/files/computers.xsd";

        XmlValidator xmlValidator = new XmlValidator();
        DeviceSaxBuilder deviceSaxBuilder = new DeviceSaxBuilder();
        xmlValidator.isFileValid(xmlFilePath, xsdFilePath);
        try {
            deviceSaxBuilder.devicesBuilder(xmlFilePath);
        } catch (DeviceException e) {
            e.printStackTrace();
        }
        System.err.println(deviceSaxBuilder.getDevices());

        System.out.println("__________");

        DeviceStaxBuilder deviceStaxBuilder = new DeviceStaxBuilder();
        try {
            deviceStaxBuilder.devicesBuilder(xmlFilePath);
        } catch (DeviceException e) {
            e.printStackTrace();
        }

        System.err.println(deviceStaxBuilder.getDevices());

    }
}
