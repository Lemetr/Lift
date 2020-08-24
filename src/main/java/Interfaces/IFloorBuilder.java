package Interfaces;

import java.util.Random;

public interface IFloorBuilder {
    void reset(int location, IButton button);
    void createPassengers(int location, int floorsNumber, Random r);
    IFloor getResult();
}
