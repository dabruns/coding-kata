import java.util.List;

public class BusDriver {
    private List<Integer> routePoints;
    private Integer gossipCounter;

    public BusDriver(List<Integer> routePoints, Integer gossipCounter) {
        this.routePoints = routePoints;
        this.gossipCounter = gossipCounter;
    }

    public List<Integer> getRoutePoints() {
        return routePoints;
    }

    public void setRoutePoints(List<Integer> routePoints) {
        this.routePoints = routePoints;
    }

    public Integer getGossipCounter() {
        return gossipCounter;
    }

    public void setGossipCounter(Integer gossipCounter) {
        this.gossipCounter = gossipCounter;
    }
}
