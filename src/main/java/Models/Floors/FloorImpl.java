package Models.Floors;

import Interfaces.IButton;
import Interfaces.IFloor;
import Interfaces.IPassenger;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Collectors;

public class FloorImpl implements IFloor {

    private final int floorNumber;
    private final IButton BUTTON;
    private Queue<IPassenger> floorPassengers;

    public FloorImpl(int floorNumber, IButton BUTTON) {
        this.floorNumber = floorNumber;
        this.BUTTON = BUTTON;
    }

    @Override
    public int getFloorNumber() {
        return floorNumber;
    }

    @Override
    public IButton getBUTTON() {
        return BUTTON;
    }

    @Override
    public Queue<IPassenger> getFloorPassengers() {
        return floorPassengers;
    }

    /**
     * Filters passengers at the floor for those who are waiting for the elevator and
     * those who have already arrived. Filters according to the principle:
     * if the passenger's destination field is equal to 0, then he has already arrived,
     * if it differs from zero, the elevator is waiting.
     *
     * @return Collection ArrayDeque<IPassenger> with people at the floor who waiting for elevator.
     */
    @Override
    public Queue<IPassenger> getWaiting(){
        return floorPassengers.stream().filter(passenger -> passenger.getPassDirection() != 0).collect(Collectors.toCollection(ArrayDeque::new));
    }

    /**
     * Filters people on the floor for those who are waiting for the elevator and
     * those who have already arrived. Filters according to the principle:
     * if a person's destination field is equal to 0, then he has already arrived,
     * if it differs from zero, he is waiting for an elevator.
     *
     * @return Collection ArrayDeque<IPassenger> with people who are already arrived on theirs floor and
     * stay on this floor permanently.
     */
    @Override
    public Queue<IPassenger> getArrived(){
        return floorPassengers.stream().filter(passenger -> passenger.getPassDirection() == 0).collect(Collectors.toCollection(ArrayDeque::new));
    }

    @Override
    public void setFloorPassengers(Queue<IPassenger> floorPassengers) {
        this.floorPassengers = floorPassengers;
    }

    @Override
    public String toString() {
        return getFloorPassengers().toString();
    }
}
