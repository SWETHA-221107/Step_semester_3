package main.java.oop.class_problems;
public class HostelAllocation {

    static class HostelRoom {

        String roomNo;
        int beds;
        int occupied;

        HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }

        void allot(String name) {

            if (occupied < beds) {
                occupied++;

                System.out.println(name
                        + " allotted to room " + roomNo);
            } else {
                System.out.println("Room " + roomNo + " is full");
            }
        }
    }

    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {

        for (int i = 0; i < rooms.length; i++) {

            if (rooms[i] != null && rooms[i].occupied < rooms[i].beds) {
                return rooms[i];
            }
        }

        return null;
    }

    static void safeAllot(HostelRoom[] rooms, String studentName) {

        HostelRoom room = findAvailableRoom(rooms);

        // Always check for null before using the object
        if (room != null) {
            room.allot(studentName);
        } else {
            System.out.println("No rooms available for "
                    + studentName);
        }
    }

    public static void main(String[] args) {

        // Case 1: Available room
        HostelRoom[] rooms1 = {
            new HostelRoom("C-214", 3, 2),
            new HostelRoom("C-507", 2, 2)
        };

        System.out.println("Case 1:");
        safeAllot(rooms1, "Divya");

        System.out.println();

        // Case 2: All rooms full
        HostelRoom[] rooms2 = {
            new HostelRoom("C-214", 3, 3),
            new HostelRoom("C-507", 2, 2)
        };

        System.out.println("Case 2:");
        safeAllot(rooms2, "Divya");
    }
}
