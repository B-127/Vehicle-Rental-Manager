package ManagerConsole;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Schedule {
    private LocalDate pickup;
    private LocalDate dropOff;

    //Class Constructors with the first one being the default constructor
    //and the second a constructor with all the instances.
    public Schedule() {
        super();
    }

    public Schedule(LocalDate pickup, LocalDate dropOff) {
        super();
        this.pickup = pickup;
        this.dropOff = dropOff;
    }

    //The getter and setter methods for this class.
    public LocalDate getPickup() {
        return pickup;
    }

    public void setPickup(LocalDate pickup) {
        this.pickup = pickup;
    }

    public LocalDate getDropOff() {
        return dropOff;
    }

    public void setDropOff(LocalDate dropOff) {
        this.dropOff = dropOff;
    }

    //this method calculates the span between two days. It is used primarily in the GUI.
    public int calculateSpan() {
        return Period.between(pickup, dropOff).getDays();
    }
}
