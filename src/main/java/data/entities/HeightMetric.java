package data.entities;

import data.enums.MetricType;
import lombok.Data;

@Data
public class HeightMetric extends Metric {

    private MetricType type = MetricType.HEIGHT;
    private Long timestamp;
    private Float value;
}
