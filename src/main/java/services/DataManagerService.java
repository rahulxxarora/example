package services;

import data.entities.Metric;
import data.entities.User;
import data.enums.MetricType;

import java.util.List;

public interface DataManagerService {

    void addData(User user, Metric metric);
    List<Metric> fetchDataByMetricInGivenDuration(User user, MetricType metricType, Long startTime, Long endTime);
    float fetchMinByMetricInGivenDuration(User user, MetricType metricType, Long startTime, Long endTime);
    float fetchMaxByMetricInGivenDuration(User user, MetricType metricType, Long startTime, Long endTime);
    float fetchAvgByMetricInGivenDuration(User user, MetricType metricType, Long startTime, Long endTime);
}
