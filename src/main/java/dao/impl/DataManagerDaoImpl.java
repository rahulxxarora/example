package dao.impl;

import dao.DataManagerDao;
import data.entities.HeightMetric;
import data.entities.Metric;
import data.entities.User;
import data.enums.MetricType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataManagerDaoImpl implements DataManagerDao {

    private final Map<String, Map<Long, Metric>> userData;

    public DataManagerDaoImpl() {
        userData = new HashMap<>();
    }

    @Override
    public void addData(User user, Metric metric) {
        if (userData.get(user.getUsername()) == null) {
            Map<Long, Metric> data = new HashMap<>();
            data.put(metric.getTimestamp(), metric);
            userData.put(user.getUsername(), data);
        }
        else {
            Map<Long, Metric> data = userData.get(user.getUsername());
            data.put(metric.getTimestamp(), metric);
            userData.put(user.getUsername(), data);
        }
    }

    @Override
    public List<Metric> fetchDataByMetricInGivenDuration(User user, MetricType metricType, Long startTime, Long endTime) {
        Map<Long, ? extends Metric> data = userData.get(user.getUsername());

        if (data == null || data.isEmpty()) {
            return new ArrayList<>();
        }

        List<Metric> response = new ArrayList<>();
        for (Map.Entry<Long, ? extends Metric> entry: data.entrySet()) {
            Long timestamp = entry.getKey();
            Metric metric = entry.getValue();
            if (timestamp < startTime || timestamp > endTime) {
                continue;
            }
            if (!metric.getType().equals(metricType)) {
                continue;
            }
            response.add(metric);
        }

        return response;
    }

    @Override
    public float fetchMinByMetricInGivenDuration(User user, MetricType metricType, Long startTime, Long endTime) {
        Map<Long, ? extends Metric> data = userData.get(user.getUsername());
        Float response = new Float(10000000);

        if (data == null || data.isEmpty()) {
            return response;
        }

        for (Map.Entry<Long, ? extends Metric> entry: data.entrySet()) {
            Long timestamp = entry.getKey();
            Metric metric = entry.getValue();
            if (timestamp < startTime || timestamp > endTime) {
                continue;
            }
            if (!metric.getType().equals(metricType)) {
                continue;
            }

            if ((Float) metric.getValue() < response) {
                response = (Float) metric.getValue();
            }
        }

        return response;
    }

    @Override
    public float fetchMaxByMetricInGivenDuration(User user, MetricType metricType, Long startTime, Long endTime) {
        Map<Long, ? extends Metric> data = userData.get(user.getUsername());
        Float response = new Float(0);

        if (data == null || data.isEmpty()) {
            return response;
        }

        for (Map.Entry<Long, ? extends Metric> entry: data.entrySet()) {
            Long timestamp = entry.getKey();
            Metric metric = entry.getValue();
            if (timestamp < startTime || timestamp > endTime) {
                continue;
            }
            if (!metric.getType().equals(metricType)) {
                continue;
            }

            if ((Float) metric.getValue() > response) {
                response = (Float) metric.getValue();
            }
        }

        return response;
    }

    @Override
    public float fetchAvgByMetricInGivenDuration(User user, MetricType metricType, Long startTime, Long endTime) {
        Map<Long, ? extends Metric> data = userData.get(user.getUsername());
        float sum = 0;
        int count = 0;

        if (data == null || data.isEmpty()) {
            return sum;
        }

        for (Map.Entry<Long, ? extends Metric> entry: data.entrySet()) {
            Long timestamp = entry.getKey();
            Metric metric = entry.getValue();
            if (timestamp < startTime || timestamp > endTime) {
                continue;
            }
            if (!metric.getType().equals(metricType)) {
                continue;
            }

            sum += (Float) metric.getValue();
            count += 1;
        }

        return (count == 0)? 0: sum/count;
    }
}
