package Logic;

import Interfaces.*;
import Models.Buttons.CallButton;
import Models.Floors.FloorImpl;
import Models.Passengers.Passenger;
import org.junit.jupiter.api.*;
import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    IConntroller controller;
    List<IFloor> floors;
    ILift lift;

    @BeforeEach
    void setUp() {
        floors = new ArrayList<>();

        IButton cBut0 = new CallButton();
        cBut0.addCall("UP");
        cBut0.addCall("UP");
        FloorImpl floor0 = new FloorImpl(0, cBut0);
        floor0.setFloorPassengers(createPassengers(0));
        floors.add(floor0);

        IButton cBut1 = new CallButton();
        cBut1.addCall("UP");
        cBut1.addCall("DOWN");
        FloorImpl floor1 = new FloorImpl(1, cBut1);
        floor1.setFloorPassengers(createPassengers(1));
        floors.add(floor1);

        IButton cBut2 = new CallButton();
        cBut2.addCall("DOWN");
        cBut2.addCall("DOWN");
        FloorImpl floor2 = new FloorImpl(2, cBut2);
        floor2.setFloorPassengers(createPassengers(2));
        floors.add(floor2);

        controller = new Controller(floors);

        try {
            Field field = controller.getClass().getDeclaredField("elevator");
            field.setAccessible(true);
            lift = (ILift) field.get(controller);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Queue<IPassenger> createPassengers(int location) {
        Queue<IPassenger> passengers = new ArrayDeque<>();
        for (int i = 0; i < 3; i++){
            passengers.offer(new Passenger(location, i));
        }
        return passengers;
    }

    @AfterEach
    void tearDown() {
        floors = null;
        controller = null;
    }

    @Test
    void TestHaveCalls() {
        floors.get(0).getBUTTON().removeCall("UP");
        floors.get(0).getBUTTON().removeCall("UP");

        assertTrue(controller.haveCalls());
        assertEquals(1, lift.getLiftDestination());
    }

    @Test
    void TestHaveCalls2() {
        floors.get(0).getBUTTON().removeCall("UP");
        floors.get(0).getBUTTON().removeCall("UP");
        floors.get(1).getBUTTON().removeCall("UP");
        floors.get(1).getBUTTON().removeCall("DOWN");
        floors.get(2).getBUTTON().removeCall("DOWN");
//        floors.get(2).getBUTTON().removeCall("DOWN");

        assertEquals(0, lift.getLiftDestination());
        assertTrue(controller.haveCalls());
        assertEquals(2, lift.getLiftDestination());
    }

    @Test
    void TestLoadLift() {
        //Elevator on the 0 floor.

        int actual0 = floors.get(0).getBUTTON().getCalls("UP");
        assertEquals(2, actual0);
        controller.loadLift(controller.getPassengersFromFloor().iterator());
        assertEquals(0, lift.getLiftLocation());
        int actual1 = floors.get(0).getFloorPassengers().size();
        assertEquals(1, actual1);
        int actual2 = lift.getLiftPassengers().size();
        assertEquals(2, actual2);
        int actual3 = floors.get(0).getBUTTON().getCalls("UP");
        assertEquals(0, actual3);
    }

    @Test
    void TestUnloadLift() {
        controller.loadLift(controller.getPassengersFromFloor().iterator());
        controller.go();
        controller.unloadLift();

        Queue<IPassenger> floor1Passengers = floors.get(1).getFloorPassengers();
        int actual0 = floor1Passengers.size();
        assertEquals(4, actual0);
        int actual1 = lift.getLiftPassengers().size();
        assertEquals(1, actual1);
        int actual2 = (int)floor1Passengers.stream().filter(passenger -> passenger.getPassDirection() == 0).count();
        assertEquals(2, actual2);
    }

    @Test
    void TestUnloadLift2() {

        controller.unloadLift();

        int actual0 = floors.get(0).getFloorPassengers().size();
        assertEquals(3, actual0);
        int actual1 = lift.getLiftPassengers().size();
        assertEquals(0, actual1);
    }
}