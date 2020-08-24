package Logic;

import Constants.Constants;
import Interfaces.*;
import Models.Lifts.Lift;
import java.util.*;

public class Controller implements IConntroller, Runnable {

    private final ILift elevator = new Lift(Constants.LiftCAPACITY);
    private final List<IFloor> iFloors;
    private final IPrinter p = new IPrinterImpl();

    public Controller(List<IFloor> floors) {
        iFloors = floors;
    }

    @Override
    public void run() {
        p.printFloorIndicator(elevator, iFloors);
        p.printStatus(Constants.arrived, elevator, iFloors);

        while (true) {
            loadLift(getPassengersFromFloor().iterator());

            p.printStatus(Constants.loaded, elevator, iFloors);

            if (elevator.getLiftDirection()==0 && !haveCalls()) break;
            go();

            p.printFloorIndicator(elevator, iFloors);
            p.printStatus(Constants.arrived, elevator, iFloors);

            unloadLift();

            p.printStatus(Constants.unloaded, elevator, iFloors);
        }
    }

    @Override
    public void go() {
        if (elevator.getLiftDirection() > 0)
            elevator.setLiftLocation(elevator.getLiftLocation() + 1);

        if (elevator.getLiftDirection() < 0)
            elevator.setLiftLocation(elevator.getLiftLocation() - 1);
    }

    @Override
    public boolean haveCalls(){
        for (IFloor floor : iFloors) {
            if (floor.getBUTTON().isCalling()){
                elevator.setLiftDestination(floor.getFloorNumber());
                return true;
            }
        }
        return false;
    }

    @Override
    public Queue<IPassenger> getPassengersFromFloor(){
        return iFloors.get(elevator.getLiftLocation()).getFloorPassengers();
    }

    @Override
    public boolean isSameDirection(IPassenger passenger){
        return passenger.getPassDirection() !=0 && passenger.getPassDirection() == elevator.getLiftDirection();
    }

    @Override
    public void loadLift(Iterator<IPassenger> iterator) {
        IPassenger next;
        while (elevator.isNotFull()) {
            if (iterator.hasNext()) {
                next = iterator.next();
                if (elevator.getLiftDirection() == 0)
                    elevator.setLiftDestination(next.getPassDestination());
                if (isSameDirection(next)) {
                    elevator.addPassenger(next);
                    iFloors.get(next.getPassLocation())
                            .getBUTTON()
                            .removeCall(next.getPassDirection() > 0 ? "UP" : "DOWN");
                    iterator.remove();
                }

            } else {
                break;
            }
        }
    }

    @Override
    public void unloadLift() {
        List<IPassenger> liftPassengers = elevator.getLiftPassengers();
        if (liftPassengers.size()==0) return;
        Iterator<IPassenger> iterator = liftPassengers.iterator();
        while (iterator.hasNext()) {
            IPassenger passenger = iterator.next();
            if (elevator.checkLocation(passenger)){
                getPassengersFromFloor().offer(passenger);
                passenger.setFinished(elevator.getLiftLocation());
                iterator.remove();
            }
        }
    }
}