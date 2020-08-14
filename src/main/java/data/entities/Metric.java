package data.entities;

import data.enums.MetricType;
import lombok.Data;

import java.util.Objects;

@Data
public abstract class Metric {

    protected Long timestamp;
    protected MetricType type;
    abstract public Object getValue();
}
