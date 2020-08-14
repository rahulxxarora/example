package services.impl;

import dao.DataManagerDao;
import data.entities.Device;
import data.entities.Metric;
import data.entities.User;
import data.enums.MetricType;
import exceptions.UserNotPresentException;
import services.AppInterface;
import services.DataManagerService;
import services.DeviceManagerService;
import services.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppInterfaceImpl implements AppInterface {

    private final UserService userService;
    private final DeviceManagerService deviceManagerService;
    private final DataManagerService dataManagerService;

    public AppInterfaceImpl(UserService userService, DeviceManagerService deviceManagerService, DataManagerService dataManagerService) {
        this.userService = userService;
        this.deviceManagerService = deviceManagerService;
        this.dataManagerService = dataManagerService;
    }

    @Override
    public boolean login(User user) throws UserNotPresentException {
        return userService.login(user.getUsername());
    }

    @Override
    public List<Device> showAllDevices(User user) {
        return deviceManagerService.fetchAllDevices(user);
    }

    @Override
    public Map<MetricType, Float> showDailyMetric(User user) {
        Map<MetricType, Float> metrics = new HashMap<>();
        // Considering 0 - 24 as the daily timestamp range
        // Could have used a rolling window here, if current ts was provided
        metrics.put(MetricType.HEIGHT, dataManagerService.fetchAvgByMetricInGivenDuration(user, MetricType.HEIGHT, 0L, 24L));
        metrics.put(MetricType.WEIGHT, dataManagerService.fetchAvgByMetricInGivenDuration(user, MetricType.WEIGHT, 0L, 24L));

        return metrics;
    }

    @Override
    public Map<MetricType, Float> showWeeklyMetric(User user) {
        Map<MetricType, Float> metrics = new HashMap<>();
        // Considering 0 - 168 as the weekly timestamp range
        // Could have used a rolling window here, if current ts was provided
        metrics.put(MetricType.HEIGHT, dataManagerService.fetchAvgByMetricInGivenDuration(user, MetricType.HEIGHT, 0L, 168L));
        metrics.put(MetricType.WEIGHT, dataManagerService.fetchAvgByMetricInGivenDuration(user, MetricType.WEIGHT, 0L, 168L));

        return metrics;
    }
}
