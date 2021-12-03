package by.petrovich.computer.parser;

import by.petrovich.computer.entity.Device;
import by.petrovich.computer.exception.DeviceException;

import java.util.List;

public interface DeviceBuilder {
    void deviceSaxBuilder(String filePath) throws DeviceException;

    List<Device> getDevices();
}
