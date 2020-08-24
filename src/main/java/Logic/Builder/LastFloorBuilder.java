package Logic.Builder;

import Constants.Constants;
import Interfaces.IButton;
import Interfaces.IFloor;
import Interfaces.IFloorBuilder;
import Interfaces.IPassenger;
import Models.Floors.FloorImpl;
import Models.Passengers.Passenger;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class LastFloorBuilder implements IFloorBuilder {

    private FloorImpl f;

    @Override
    public void reset(int location, IButton button) {
        f = new FloorImpl(location, button);
    }

    @Override
    public void createPassengers(int location, int floorsNumber, Random r) {

        Queue<IPassenger> floorPassengers = new ArrayDeque<>();
        for (int j = 0; j < r.nextInt(Constants.MaxPASSENGERS + 1); j++) {
            int destination = r.nextInt(floorsNumber);
            floorPassengers.add(new Passenger(location, destination));
        }
        f.setFloorPassengers(floorPassengers);

    }

    @Override
    public IFloor getResult() {
        return f;
    }
}
