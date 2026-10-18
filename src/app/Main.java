package app;

import controller.RentalController;
import model.Kendaraan;

public class Main {
    public static void main(String[] args) {
       RentalController controller = new RentalController();

       controller.tambahData(new Kendaraan("A001", "Toyota", "MOBIL", 100000));
       controller.tambahData(new Kendaraan("A002", "Ferrari", "MOBIL", 10000000));
       controller.tambahData(new Kendaraan("A003", "Toyota", "MOBIL", 100000));

       controller.jalankan();
    }
}
