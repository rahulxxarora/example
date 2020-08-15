package data.entities;

import data.enums.MetricType;
import lombok.Data;

@Data
public class HeightMetric extends Metric {

    public HeightMetric() {
        type = MetricType.HEIGHT;
    }
    private Float value;
}
