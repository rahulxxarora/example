package data.entities;

import data.enums.MetricType;
import lombok.Data;

@Data
public class WeightMetric extends Metric {

    private MetricType type = MetricType.WEIGHT;
    private Long timestamp;
    private Float value;
}
