package services.impl;

import dao.DataManagerDao;
import dao.impl.DataManagerDaoImpl;
import data.entities.Metric;
import data.entities.User;
import data.enums.MetricType;
import services.DataManagerService;

import java.util.List;

public class DataManagerServiceImpl implements DataManagerService {

    private final DataManagerDao dataManagerDao;

    public DataManagerServiceImpl() {
        dataManagerDao = new DataManagerDaoImpl();
    }

    @Override
    public void addData(User user, Metric metric) {
        dataManagerDao.addData(user, metric);
    }

    @Override
    public List<Metric> fetchDataByMetricInGivenDuration(User user, MetricType metricType, Long startTime, Long endTime) {
        return dataManagerDao.fetchDataByMetricInGivenDuration(user, metricType, startTime, endTime);
    }

    @Override
    public float fetchMinByMetricInGivenDuration(User user, MetricType metricType, Long startTime, Long endTime) {
        return dataManagerDao.fetchMinByMetricInGivenDuration(user, metricType, startTime, endTime);
    }

    @Override
    public float fetchMaxByMetricInGivenDuration(User user, MetricType metricType, Long startTime, Long endTime) {
        return dataManagerDao.fetchMaxByMetricInGivenDuration(user, metricType, startTime, endTime);
    }

    @Override
    public float fetchAvgByMetricInGivenDuration(User user, MetricType metricType, Long startTime, Long endTime) {
        return dataManagerDao.fetchAvgByMetricInGivenDuration(user, metricType, startTime, endTime);
    }
}
