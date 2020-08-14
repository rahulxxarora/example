package dao;

import data.entities.Device;
import data.entities.User;
import exceptions.DeviceAlreadyRegisteredException;

import java.util.List;

public interface DeviceManagerDao {

    void register(User user, Device device) throws DeviceAlreadyRegisteredException;
    List<Device> fetchAllDevices(User user);
}
