package by.petrovich.computer.parser;

import by.petrovich.computer.entity.DeviceAbstract;
import by.petrovich.computer.exception.DeviceException;

import java.util.List;

public interface DeviceBuilder {
    void parseDevices(String filePath) throws DeviceException;

    List<DeviceAbstract> getDevices();
}
