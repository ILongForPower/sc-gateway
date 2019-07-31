package org.sc.gateway.robin;

import com.netflix.loadbalancer.Server;

import java.util.Objects;

public class WeightServer {
    Server server;
    Integer weight;

    public WeightServer(Server server, Integer weight) {
        this.server = server;
        this.weight = weight;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(com.netflix.loadbalancer.Server server) {
        this.server = server;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightServer that = (WeightServer) o;
        return Objects.equals(server, that.server) &&
                Objects.equals(weight, that.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(server, weight);
    }
}
