package Interfaces;

import java.util.Queue;

public interface IFloor {
    int getFloorNumber();
    IButton getBUTTON();
    Queue<IPassenger> getFloorPassengers();
    Queue<IPassenger> getWaiting();
    Queue<IPassenger> getArrived();
    void setFloorPassengers(Queue<IPassenger> floorPassengers);
}
