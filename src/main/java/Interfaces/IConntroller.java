package Interfaces;

import java.util.Iterator;
import java.util.Queue;

public interface IConntroller {
    void go();
    boolean haveCalls();
    Queue<IPassenger> getPassengersFromFloor();
    boolean isSameDirection(IPassenger passenger);
    void loadLift(Iterator<IPassenger> iterator);
    void unloadLift();
}
