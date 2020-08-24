package Interfaces;

import java.util.List;

public interface ILift {

    int getLiftDirection();
    int getLiftLocation();
    void setLiftLocation(int location);
    int getLiftDestination();
    void setLiftDestination(int destination);
    List<IPassenger> getLiftPassengers();
    boolean checkLocation(IPassenger passenger);
    int getLiftCapacity();
    boolean isNotFull();
    boolean addPassenger(IPassenger passenger);

}
