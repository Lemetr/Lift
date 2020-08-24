package Models.Floors;

import Interfaces.IFloor;
import Interfaces.IPassenger;
import Models.Buttons.CallButton;
import Models.Passengers.Passenger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayDeque;
import java.util.Queue;
import static org.junit.jupiter.api.Assertions.*;

class FloorImplTest {

    Queue<IPassenger> floorPassenger;
    IFloor floor;

    void setUp(int location, int destination) {

        floor = new FloorImpl(destination, new CallButton());
        floorPassenger = new ArrayDeque<>();
        floorPassenger.offer(new Passenger(location, destination));
        floorPassenger.offer(new Passenger(location, destination));
        floorPassenger.offer(new Passenger(location, destination));
        floorPassenger.offer(new Passenger(location, destination));
        floorPassenger.offer(new Passenger(location, destination));
        floorPassenger.offer(new Passenger(location, destination));
        floorPassenger.offer(new Passenger(location, destination));
        floorPassenger.offer(new Passenger(location, destination));
        floorPassenger.offer(new Passenger(location, destination));
        floorPassenger.offer(new Passenger(location, destination));
        floor.setFloorPassengers(floorPassenger);

    }

    @AfterEach
    void tearDown() {
        floorPassenger = null;
        floor = null;
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12})
    void TestGetWaiting(int destination) {
        int location = 11;
        setUp(location, destination);
        int expected = 0;
        switch (destination){
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 12: expected = 10; break;
            case 11: expected = 0; break;
        }

        assertEquals(expected, floor.getWaiting().size());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12})
    void TestGetWaiting2(int destination) {
        int location = 5;
        setUp(location, destination);
        int expected = 0;
        switch (destination){
            case 5: expected = 0; break;
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12: expected = 10; break;
        }

        assertEquals(expected, floor.getWaiting().size());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12})
    void TestGetArrived(int destination) {
        int location = 1;
        setUp(location, destination);
        int expected = 0;
        switch (destination){
            case 1: expected = 10; break;
            case 0:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12: expected = 0; break;
        }

        assertEquals(expected, floor.getArrived().size());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12})
    void TestGetArrived2(int destination) {
        int location = 11;
        setUp(location, destination);
        int expected = 0;
        switch (destination){
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 12: expected = 0; break;
            case 11: expected = 10; break;
        }

        assertEquals(expected, floor.getArrived().size());
    }
}