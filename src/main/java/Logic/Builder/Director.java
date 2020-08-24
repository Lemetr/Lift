package Logic.Builder;

import Constants.Constants;
import Interfaces.IFloor;
import Interfaces.IFloorBuilder;
import Models.Buttons.CallButton;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Director {

    private final Random r = new Random();

    public IFloor constructExtremityFloor(IFloorBuilder builder, int location, int floorsNumber){
        builder.reset(location, new CallButton());
        builder.createPassengers(location, floorsNumber, r);
        return builder.getResult();
    }

    public IFloor constructMiddleFloor(IFloorBuilder builder, int location, int floorsNumber){

        builder.reset(location, new CallButton());
        builder.createPassengers(location, floorsNumber, r);
        return builder.getResult();
    }

    public List<IFloor> make(){

        int floorsCount = Constants.MinFLOORS + r.nextInt(Constants.MaxFLOORS-Constants.MinFLOORS);
        List<IFloor> floors = new ArrayList<>();

        floors.add(constructExtremityFloor(new FirstFloorBuilder(), 0,floorsCount-1));
        for (int i = 1; i < floorsCount-1; i++) {
            floors.
                    add(constructMiddleFloor(new MiddleFloorBuilder(), i, floorsCount-1));
        }
        floors.add(constructExtremityFloor(new LastFloorBuilder(), floorsCount-1, floorsCount-1));

        return floors;
    }
}
