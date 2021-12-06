package by.petrovich.computer.parser;

import by.petrovich.computer.entity.Cpu;
import by.petrovich.computer.entity.DeviceAbstract;
import by.petrovich.computer.entity.Hdd;
import by.petrovich.computer.entity.type.Country;
import by.petrovich.computer.entity.type.Peripheral;
import by.petrovich.computer.exception.DeviceException;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeviceSaxBuilderTest {
    private String filePath = "src/test/resources/files/computers.xml";

    @Test
    public void testDeviceSaxBuilder() throws DeviceException {

        DeviceSaxBuilder deviceSaxBuilder = new DeviceSaxBuilder();
        deviceSaxBuilder.parseDevices(filePath);
        List<DeviceAbstract> actual = deviceSaxBuilder.getDevices();
        List<DeviceAbstract> expected = List.of(new Cpu("ID1", "pic1.png", "AMD A4-5300 APU OEM",
                LocalDate.of(2021, 10, 03), Country.CANADA, 65.24, Peripheral.IN_CASE,
                "FM2", true, 5300), new Hdd("ID9", "pic9.png", "Seagate Exos X16",
                LocalDate.of(2020, 03, 01), Country.USA, 78.45, Peripheral.IN_CASE,
                "SATA", true, 16));
        assertThat(actual).containsExactlyElementsOf(expected);
    }
}