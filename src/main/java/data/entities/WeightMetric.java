package data.entities;

import data.enums.MetricType;
import lombok.Data;

@Data
public class WeightMetric extends Metric {

    public WeightMetric() {
        type = MetricType.WEIGHT;
    }
    private Float value;
}
