package Models.Lifts;

import Interfaces.ILift;
import Interfaces.IPassenger;
import java.util.ArrayList;
import java.util.List;


public class Lift implements ILift {

    private final int capacity;
    private int location = 0;
    private int destination = 0;
    private final List<IPassenger> inLiftPassengers;

    public Lift(int capacity) {
        this.capacity = capacity;
        inLiftPassengers = new ArrayList<>();
    }

    /**
     * defining which way lift goes at this moment, up or down.
     * @return POSITIVE number means lift goes up, NEGATIVE - down,
     * ZERO - lift stop, no passengers, no calls from floors.
     */
    @Override
    public int getLiftDirection() {
        return Integer.signum(destination-location);
    }

    @Override
    public int getLiftDestination() {
        return destination;
    }

    @Override
    public int getLiftLocation() {
        return location;
    }

    @Override
    public void setLiftLocation(int location) {
        this.location = location;
    }

    @Override
    public void setLiftDestination(int dest) {
        if(getLiftDirection() > 0) {
            if(dest > destination) destination = dest;
        } else if (getLiftDirection() < 0) {
            if (dest < destination) destination = dest;
        } else destination = dest;
    }

    @Override
    public List<IPassenger> getLiftPassengers() {
        return inLiftPassengers;
    }

    @Override
    public boolean checkLocation(IPassenger passenger) {
        return passenger.getPassDestination()==location;
    }

    @Override
    public int getLiftCapacity() {
        return capacity;
    }

    @Override
    public boolean isNotFull() {
        return inLiftPassengers.size() < capacity;
    }

    @Override
    public boolean addPassenger(IPassenger passenger){
        inLiftPassengers.add(passenger);
        setLiftDestination(passenger.getPassDestination());
        return true;
    }

    @Override
    public String toString() {
        return inLiftPassengers.toString();
    }
}
