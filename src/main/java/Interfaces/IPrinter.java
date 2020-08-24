package Interfaces;

import java.util.List;

public interface IPrinter {
    void printStatus(String status, ILift elevator, List<IFloor> floors);
    void printLiftStatus(ILift elevator);
    void printWaitingPeopleStatus(ILift elevator, List<IFloor> floors);
    void printArrivedPeopleStatus(ILift elevator, List<IFloor> floors);
    void printFloorIndicator(ILift elevator, List<IFloor> floors);
}
