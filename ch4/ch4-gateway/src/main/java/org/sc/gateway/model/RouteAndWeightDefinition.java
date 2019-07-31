package org.sc.gateway.model;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.Objects;

public class RouteAndWeightDefinition extends RouteDefinition {

    private String path;
    private String weight;
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RouteAndWeightDefinition that = (RouteAndWeightDefinition) o;
        return Objects.equals(path, that.path) &&
                Objects.equals(weight, that.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), path, weight);
    }
}
