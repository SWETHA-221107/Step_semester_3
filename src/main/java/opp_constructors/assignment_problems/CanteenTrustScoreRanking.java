package main.java.opp_constructors.assignment_problems;
class Canteen {

    private String canteenCode;
    private String canteenName;
    private int trustScore;


    // Full constructor
    public Canteen(
            String canteenCode,
            String canteenName,
            int trustScore) {

        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }


    // Second constructor with default trust score
    public Canteen(
            String canteenCode,
            String canteenName) {

        this(
                canteenCode,
                canteenName,
                3
        );
    }


    /*
     * Comparison order:
     *
     * 1. Higher trust score first.
     * 2. If scores are equal, compare canteen codes
     *    ignoring letter case.
     * 3. If codes are equal ignoring case, compare
     *    canteen name length.
     *
     * compareTo() returns:
     * negative -> this comes before other
     * zero     -> both are equal
     * positive -> this comes after other
     */
    public int compareTo(Canteen other) {

        // Higher trust score comes first.
        if (this.trustScore != other.trustScore) {

            return Integer.compare(
                    other.trustScore,
                    this.trustScore
            );
        }


        // Tie-break using code,
        // without changing stored/displayed code.
        int codeResult =
                this.canteenCode.compareToIgnoreCase(
                        other.canteenCode
                );

        if (codeResult != 0) {

            return codeResult;
        }


        // Final tie-break: name length
        return Integer.compare(
                this.canteenName.length(),
                other.canteenName.length()
        );
    }


    public String getCanteenCode() {

        return canteenCode;
    }


    public static Canteen[] rankCanteens(
            Canteen[] canteens) {

        // Copy references into a new array.
        // Original array is not changed.
        Canteen[] result =
                new Canteen[canteens.length];

        for (int i = 0; i < canteens.length; i++) {

            result[i] = canteens[i];
        }


        // Manual Bubble Sort.
        // No built-in sorting utility is used.
        for (int i = 0;
             i < result.length - 1;
             i++) {

            for (int j = 0;
                 j < result.length - 1 - i;
                 j++) {

                if (result[j].compareTo(
                        result[j + 1]) > 0) {

                    Canteen temp = result[j];

                    result[j] = result[j + 1];

                    result[j + 1] = temp;
                }
            }
        }

        return result;
    }
}


public class CanteenTrustScoreRanking {

    public static void main(String[] args) {

        Canteen[] canteens = {

                new Canteen(
                        "HB3-C",
                        "Spice Junction",
                        3
                ),

                new Canteen(
                        "hb1-c",
                        "Grand Mess",
                        5
                ),

                new Canteen(
                        "HB2-C",
                        "Southern Treats"
                )
        };


        Canteen[] ranked =
                Canteen.rankCanteens(canteens);


        System.out.println("Ranked canteens:");

        for (Canteen canteen : ranked) {

            System.out.println(
                    canteen.getCanteenCode()
            );
        }
    }
}
