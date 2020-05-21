package ManagerConsole;

import java.sql.SQLException;

public interface RentalVehicleManager {
    public void addVehicle() throws Exception;
    public void deleteVehicle() throws Exception;
    public void printList() throws Exception;
    public void update() throws Exception;
    public void generateReport() throws Exception;
}
