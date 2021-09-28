import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<BusDriver> busDriversWithRouteOverview = new ArrayList<>();
        busDriversWithRouteOverview.add(new BusDriver(Arrays.asList(3, 1, 2, 3), 1));
        busDriversWithRouteOverview.add(new BusDriver(Arrays.asList(3, 2, 3, 1), 1));
        busDriversWithRouteOverview.add(new BusDriver(Arrays.asList(4, 2, 3, 4, 5), 1));

//        busDriversWithRouteOverview.add(new BusDriver(Arrays.asList(2, 1, 2), 1));
//        busDriversWithRouteOverview.add(new BusDriver(Arrays.asList(5, 2, 8), 1));

//        busDriversWithRouteOverview.add(new BusDriver(Arrays.asList(1, 2, 3), 1));
//        busDriversWithRouteOverview.add(new BusDriver(Arrays.asList(1, 2, 3), 1));

//        busDriversWithRouteOverview.add(new BusDriver(Arrays.asList(7, 11, 2, 2, 4, 8, 2, 2), 1));
//        busDriversWithRouteOverview.add(new BusDriver(Arrays.asList(3, 0, 11, 8), 1));
//        busDriversWithRouteOverview.add(new BusDriver(Arrays.asList(5, 11, 8, 10, 3, 11), 1));
//        busDriversWithRouteOverview.add(new BusDriver(Arrays.asList(5, 9, 2, 5, 0, 3), 1));
//        busDriversWithRouteOverview.add(new BusDriver(Arrays.asList(7, 4, 8, 2, 8, 1, 0, 5), 1));
//        busDriversWithRouteOverview.add(new BusDriver(Arrays.asList(3, 6, 8, 9), 1));
//        busDriversWithRouteOverview.add(new BusDriver(Arrays.asList(4, 2, 11, 3, 3), 1));

//        busDriversWithRouteOverview.add(new BusDriver(Arrays.asList(4, 2, 3, 4, 5), 1));
        List<BusDriver> busDrivers = createBusDriverWithRouteListForADay(busDriversWithRouteOverview);
        busDrivers.forEach(busDriver -> {
            if (busDriver.getRoutePoints().size() != 480) {
                throw new RuntimeException("The bus driver routes have to be 480 stops!");
            }
        });
        int offset = 1;
        int stops = 0;
        try {
            for (int i = 0; i < busDrivers.size(); i++) {
                stops = getStops(busDrivers, stops, i);
            }
        } catch (NoMatchException e) {
            stops = 0;
        }
        if (stops == 0) {
            System.out.println("Never!");
        } else {
            int total = stops + offset;
            System.out.println("Stops: " + total);
        }
    }

    private static int getStops(List<BusDriver> busDrivers, int stops, int i) {
        try {
            if (busDrivers.get(i).getGossipCounter() != busDrivers.size()) {
                try {
                    stops = stops + 1;
                    checkStops(busDrivers.get(i), busDrivers.get(i + 1));
                } catch (IndexOutOfBoundsException e) {
                    stops = stops + 1;
                    getStops(busDrivers, stops, 0);
                }
            } else {
                getStops(busDrivers, stops, i + 1);
            }
            return stops;
        } catch (IndexOutOfBoundsException e) {
            return stops;
        }
    }

    private static void checkStops(BusDriver busDriverA, BusDriver busDriverB) {
        for (int i = 0; i < 480; i++) {
            if (busDriverA.getRoutePoints().get(i).equals(busDriverB.getRoutePoints().get(i))) {
                busDriverA.setGossipCounter(busDriverA.getGossipCounter() + 1);
                busDriverB.setGossipCounter(busDriverB.getGossipCounter() + 1);
                break;
            }
            if (i == 479) {
                throw new NoMatchException();
            }
        }
    }

    private static List<BusDriver> createBusDriverWithRouteListForADay(List<BusDriver> busDrivers) {
        for (BusDriver busDriver : busDrivers) {
            List<Integer> routePoints = new ArrayList<>();
            int time = 480;
            while (time > 0) {
                for (Integer route : busDriver.getRoutePoints()) {
                    routePoints.add(route);
                    time = time - 1;
                }
            }
            busDriver.setRoutePoints(routePoints);
        }
        return busDrivers;
    }

}
