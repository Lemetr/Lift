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

public class MiddleFloorBuilder implements IFloorBuilder {

    private FloorImpl f;

    @Override
    public void reset(int location, IButton button) {
        f = new FloorImpl(location, button);
    }

    @Override
    public void createPassengers(int location, int floorsNumber, Random r){

        Queue<IPassenger> floorPassengers = new ArrayDeque<>();
        for (int j = 0; j < r.nextInt(Constants.MaxPASSENGERS+1); j++){
            int destination = r.nextInt(floorsNumber+1);
            if (destination == location && destination < floorsNumber){
                destination++;
            } else if (destination == location && destination == floorsNumber){
                destination = 1;
            }
            floorPassengers.offer(new Passenger(location, destination));
            f.getBUTTON().addCall(destination-location > 0 ? "UP" : "DOWN");
        }
        f.setFloorPassengers(floorPassengers);

    }

    @Override
    public IFloor getResult(){
        return f;
    }
}
