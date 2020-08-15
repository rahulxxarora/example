package dao.impl;

import dao.DataManagerDao;
import data.entities.Metric;
import data.entities.User;
import data.enums.MetricType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataManagerDaoImpl implements DataManagerDao {

    private final Map<String, List<Metric>> userData;

    public DataManagerDaoImpl() {
        userData = new HashMap<>();
    }

    @Override
    public void addData(User user, Metric metric) {
        List<Metric> metrics = userData.get(user.getUsername());
        if (metrics == null) {
            metrics = new ArrayList<>();
        }
        metrics.add(metric);
        userData.put(user.getUsername(), metrics);
    }

    @Override
    public List<Metric> fetchDataByMetricInGivenDuration(User user, MetricType metricType, Long startTime, Long endTime) {
        List<Metric> metrics = userData.get(user.getUsername());

        if (metrics == null || metrics.isEmpty()) {
            return new ArrayList<>();
        }

        List<Metric> response = new ArrayList<>();
        for (Metric metric: metrics) {
            if (metric.getTimestamp() < startTime || metric.getTimestamp() > endTime) {
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
        List<Metric> metrics = userData.get(user.getUsername());
        Float response = 10000000F;

        if (metrics == null || metrics.isEmpty()) {
            return response;
        }

        for (Metric metric: metrics) {
            if (metric.getTimestamp() < startTime || metric.getTimestamp() > endTime) {
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
        List<Metric> metrics = userData.get(user.getUsername());
        Float response = 0F;

        if (metrics == null || metrics.isEmpty()) {
            return response;
        }

        for (Metric metric: metrics) {
            if (metric.getTimestamp() < startTime || metric.getTimestamp() > endTime) {
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
        List<Metric> metrics = userData.get(user.getUsername());
        float sum = 0;
        int count = 0;

        if (metrics == null || metrics.isEmpty()) {
            return 0;
        }

        for (Metric metric: metrics) {
            if (metric.getTimestamp() < startTime || metric.getTimestamp() > endTime) {
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
