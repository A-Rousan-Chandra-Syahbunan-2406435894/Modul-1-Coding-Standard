package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryTest {
    CarRepository carRepository;

    @BeforeEach
    void setUp() {
        carRepository = new CarRepository();
    }

    @Test
    void testCreateWithId() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        carRepository.create(car);
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", carRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6").getCarId());
    }

    @Test
    void testCreateWithNullId() {
        Car car = new Car();
        car.setCarId(null);
        carRepository.create(car);
        assertNotNull(car.getCarId());
    }

    @Test
    void testFindAll() {
        Car car = new Car();
        carRepository.create(car);
        Iterator<Car> iterator = carRepository.findAll();
        assertTrue(iterator.hasNext());
    }

    @Test
    void testFindByIdSuccess() {
        Car car = new Car();
        car.setCarId("123");
        carRepository.create(car);
        assertNotNull(carRepository.findById("123"));
    }


    @Test
    void testUpdateSuccess() {
        Car car = new Car();
        car.setCarId("123");
        car.setCarName("Old Name");
        carRepository.create(car);

        Car updatedCar = new Car();
        updatedCar.setCarName("New Name");
        updatedCar.setCarColor("Red");
        updatedCar.setCarQuantity(5);

        Car result = carRepository.update("123", updatedCar);
        assertNotNull(result);
        assertEquals("New Name", car.getCarName());
    }

    @Test
    void testFindByIdNotFound() {
        // Tambahin barang dulu biar perulangan 'for' nya jalan
        Car car = new Car();
        car.setCarId("123");
        carRepository.create(car);

        // Baru cari ID yang gak ada
        assertNull(carRepository.findById("non-existent-id"));
    }

    @Test
    void testUpdateNotFound() {
        // Tambahin barang dulu
        Car car = new Car();
        car.setCarId("123");
        carRepository.create(car);

        Car updatedCar = new Car();
        // Cari ID yang beda, pas list-nya ada isinya
        Car result = carRepository.update("non-existent-id", updatedCar);
        assertNull(result);
    }

    @Test
    void testDelete() {
        Car car = new Car();
        car.setCarId("123");
        carRepository.create(car);
        carRepository.delete("123");
        assertNull(carRepository.findById("123"));
    }
}