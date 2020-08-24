package Interfaces;

public interface IPassenger {
    int getPassDirection();
    void setPassDirection(int direction);
    int getPassLocation();
    void setPassLocation(int location);
    int getPassDestination();
    void setPassDestination(int destination);
    void setFinished(int location);

}
