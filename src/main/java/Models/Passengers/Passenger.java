package Models.Passengers;

import Interfaces.IPassenger;

public class Passenger implements IPassenger {

    private int location;
    private int destination;
    private int direction;

    public Passenger(int location, int destination) {
        this.location = location;
        this.destination = destination;
        setPassDirection(destination-location);
    }

    /**
     * defining the way the passenger wants to go, up or down.
     * @return POSITIVE number means he(she) wants go up, NEGATIVE - down,
     * ZERO - destination was reached.
     */
    @Override
    public int getPassDirection() {
        return Integer.signum(direction);
    }

    @Override
    public void setPassDirection(int direction) { this.direction = direction;}

    @Override
    public int getPassLocation() {
        return location;
    }

    @Override
    public void setPassLocation(int location) { this.location = location; }

    @Override
    public int getPassDestination() {
        return destination;
    }

    @Override
    public void setPassDestination(int destination) {
        this.destination = destination;
    }

    @Override
    public void setFinished(int location) {
        setPassLocation(location);
        setPassDirection(0);
    }

    @Override
    public String toString() {
        return Integer.toString(getPassDestination());
    }
}
