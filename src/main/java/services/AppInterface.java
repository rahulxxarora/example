package services;

import data.entities.Device;
import data.entities.Metric;
import data.entities.User;
import data.enums.MetricType;
import exceptions.UserNotPresentException;

import java.util.List;
import java.util.Map;

public interface AppInterface {

    boolean login(User user) throws UserNotPresentException;
    List<Device> showAllDevices(User user);
    Map<MetricType, Float> showDailyMetric(User user);
    Map<MetricType, Float> showWeeklyMetric(User user);
}
