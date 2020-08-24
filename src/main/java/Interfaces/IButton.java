package Interfaces;

public interface IButton {

    void addCall(String direction);
    void removeCall(String direction);
    int getCalls(String direction);
    boolean isCalling();
}
