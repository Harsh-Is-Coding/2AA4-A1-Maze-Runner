package ca.mcmaster.se2aa4.mazerunner;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(char move);
}