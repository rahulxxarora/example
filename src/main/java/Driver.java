import data.entities.*;
import data.enums.MetricType;
import services.AppInterface;
import services.DataManagerService;
import services.DeviceManagerService;
import services.UserService;
import services.impl.AppInterfaceImpl;
import services.impl.DataManagerServiceImpl;
import services.impl.DeviceManagerServiceImpl;
import services.impl.UserServiceImpl;

import java.util.List;
import java.util.Map;

public class Driver {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        DataManagerService dataManagerService = new DataManagerServiceImpl();
        DeviceManagerService deviceManagerService = new DeviceManagerServiceImpl();
        AppInterface appInterface = new AppInterfaceImpl(userService, deviceManagerService, dataManagerService);

        User user = new User();
        user.setUsername("rahul");

        try {
            userService.register(user.getUsername());
            System.out.println("Registered successfully...\n");
        } catch (Exception e) {

        }

        System.out.println("Logging in...\n");
        try {
            appInterface.login(user);
            System.out.println("Logged in successfully...\n");
        } catch (Exception e) {

        }

        Device device = new Device();
        device.setTag("mobile");
        try {
            deviceManagerService.register(user, device);
            System.out.println("Device registered successfully...\n");
        } catch (Exception e) {

        }

        HeightMetric heightMetric = new HeightMetric();
        heightMetric.setTimestamp(1L);
        heightMetric.setValue(100F);

        System.out.println("Adding data ...\n");
        dataManagerService.addData(user, heightMetric);

        heightMetric = new HeightMetric();
        heightMetric.setTimestamp(4L);
        heightMetric.setValue(110F);

        System.out.println("Adding data ...\n");
        dataManagerService.addData(user, heightMetric);

        WeightMetric weightMetric = new WeightMetric();
        weightMetric.setTimestamp(1L);
        weightMetric.setValue(100F);

        System.out.println("Adding data ...\n");
        dataManagerService.addData(user, weightMetric);

        System.out.println("Fetching all devices ...\n");
        List<Device> devices = appInterface.showAllDevices(user);
        for (Device device1: devices) {
            System.out.print(device1.getTag() + " ");
        }

        System.out.println();

        System.out.println("Fetching daily average for all metrics ...\n");
        Map<MetricType, Float> dailyMetricData = appInterface.showDailyMetric(user);
        for (Map.Entry<MetricType, Float> entry: dailyMetricData.entrySet()) {
            System.out.println(entry.getKey().name() + " " + entry.getValue());
        }

        System.out.println("Fetching weekly average for all metrics ...\n");
        Map<MetricType, Float> weeklyMetricData = appInterface.showWeeklyMetric(user);
        for (Map.Entry<MetricType, Float> entry: weeklyMetricData.entrySet()) {
            System.out.println(entry.getKey().name() + " " + entry.getValue());
        }
    }
}
