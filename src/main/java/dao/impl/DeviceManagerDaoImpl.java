package dao.impl;

import dao.DeviceManagerDao;
import data.entities.Device;
import data.entities.User;
import exceptions.DeviceAlreadyRegisteredException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceManagerDaoImpl implements DeviceManagerDao {

    private final Map<String, List<Device>> userDevices;

    public DeviceManagerDaoImpl() {
        userDevices = new HashMap<>();
    }

    @Override
    public void register(User user, Device device) throws DeviceAlreadyRegisteredException {
        List<Device> devices = userDevices.get(user.getUsername());

        if (devices == null || devices.isEmpty()) {
            devices = new ArrayList<>();
        }
        else {
            for (Device registeredDevice: devices) {
                if (registeredDevice.getTag().equals(device.getTag())) {
                    throw new DeviceAlreadyRegisteredException();
                }
            }
        }

        devices.add(device);
        userDevices.put(user.getUsername(), devices);
    }

    @Override
    public List<Device> fetchAllDevices(User user) {
        List<Device> devices = userDevices.get(user.getUsername());

        if (devices == null || devices.isEmpty()) {
            return new ArrayList<>();
        }

        return devices;
    }
}
