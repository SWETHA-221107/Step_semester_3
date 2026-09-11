package main.java.opp_constructors.class_problems;
import java.util.Arrays;

class BusRoute {

    private String routeCode;
    private String routeName;
    private int priority;


    // Full constructor
    public BusRoute(
            String routeCode,
            String routeName,
            int priority) {

        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }


    // Two-argument constructor
    // Default priority is 5.
    public BusRoute(
            String routeCode,
            String routeName) {

        this(
                routeCode,
                routeName,
                5
        );
    }


    public int compareTo(BusRoute other) {

        // 1. Higher priority comes first.
        if (this.priority != other.priority) {

            return Integer.compare(
                    other.priority,
                    this.priority
            );
        }


        // 2. If priority is same, compare route name.
        int nameResult =
                this.routeName.compareToIgnoreCase(
                        other.routeName
                );

        if (nameResult != 0) {
            return nameResult;
        }


        // 3. If name is also same, compare route code.
        return this.routeCode.compareToIgnoreCase(
                other.routeCode
        );
    }


    public String getRouteCode() {
        return routeCode;
    }


    public static BusRoute[] rankRoutes(
            BusRoute[] routes) {

        // Make a copy so original array is not changed.
        BusRoute[] result =
                Arrays.copyOf(
                        routes,
                        routes.length
                );


        // Bubble sort
        for (int i = 0; i < result.length - 1; i++) {

            for (int j = 0;
                 j < result.length - 1 - i;
                 j++) {

                if (result[j].compareTo(
                        result[j + 1]) > 0) {

                    BusRoute temp = result[j];

                    result[j] = result[j + 1];

                    result[j + 1] = temp;
                }
            }
        }

        return result;
    }
}


public class BusRouteRankingEngine {

    public static void main(String[] args) {

        BusRoute[] routes = {

            new BusRoute(
                    "RT205L",
                    "Airport Express",
                    3
            ),

            new BusRoute(
                    "rt201j",
                    "City Central",
                    4
            ),

            new BusRoute(
                    "RT299T",
                    "Night Service"
            )
        };


        BusRoute[] ranked =
                BusRoute.rankRoutes(routes);


        System.out.println("Ranked routes:");

        for (BusRoute route : ranked) {

            System.out.println(
                    route.getRouteCode()
            );
        }
    }
}