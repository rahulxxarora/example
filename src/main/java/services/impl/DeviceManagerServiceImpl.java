package services.impl;

import dao.DeviceManagerDao;
import dao.impl.DeviceManagerDaoImpl;
import data.entities.Device;
import data.entities.User;
import exceptions.DeviceAlreadyRegisteredException;
import services.DeviceManagerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceManagerServiceImpl implements DeviceManagerService {

    private final DeviceManagerDao deviceManagerDao;

    public DeviceManagerServiceImpl() {
        deviceManagerDao = new DeviceManagerDaoImpl();
    }

    @Override
    public void register(User user, Device device) throws DeviceAlreadyRegisteredException {
        deviceManagerDao.register(user, device);
    }

    @Override
    public List<Device> fetchAllDevices(User user) {
        return deviceManagerDao.fetchAllDevices(user);
    }
}
