package Logic;

import Interfaces.IFloor;
import Interfaces.ILift;
import Interfaces.IPrinter;
import java.util.Collections;
import java.util.List;

public class IPrinterImpl implements IPrinter {

    @Override
    public void printStatus(String status, ILift elevator, List<IFloor> floors){
        System.out.printf("%-22s",status);
        printWaitingPeopleStatus(elevator, floors);
        printLiftStatus(elevator);
        printArrivedPeopleStatus(elevator, floors);
    }

    @Override
    public void printLiftStatus(ILift elevator) {
        System.out.print(elevator);
    }

    @Override
    public void printWaitingPeopleStatus(ILift elevator, List<IFloor> floors) {
        System.out.print(floors.get(elevator.getLiftLocation()).getWaiting() + "--->");
    }

    @Override
    public void printArrivedPeopleStatus(ILift elevator, List<IFloor> floors){
        System.out.println(" ---> " + floors.get(elevator.getLiftLocation()).getArrived());
    }

    @Override
    public void printFloorIndicator(ILift elevator, List<IFloor> floors) {

        int liftLocation = elevator.getLiftLocation();
        int size = floors.size()-1;
        String sign = "-";
        if (elevator.getLiftDirection() > 0) sign = "^";
        if (elevator.getLiftDirection() < 0) sign = "v";
        String s = String.join("", Collections.nCopies(size - (size-liftLocation), " _"))
                + " " + liftLocation + " "
                + String.join("", Collections.nCopies(size-liftLocation, "_ "));
        System.out.printf("%n%1$s %2$s %1$s%n", sign, s);
    }

}
