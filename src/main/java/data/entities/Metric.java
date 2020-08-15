package data.entities;

import data.enums.MetricType;
import lombok.Data;

@Data
public abstract class Metric {

    protected Long timestamp;
    protected MetricType type;

    public abstract Object getValue();
}
