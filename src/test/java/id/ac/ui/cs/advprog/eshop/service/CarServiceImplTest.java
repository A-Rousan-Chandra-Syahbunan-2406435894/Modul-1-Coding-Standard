package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    void testCreate() {
        Car car = new Car();
        when(carRepository.create(car)).thenReturn(car);
        Car result = carService.create(car);
        assertEquals(car, result);
    }

    @Test
    void testFindAll() {
        when(carRepository.findAll()).thenReturn(Collections.emptyIterator());
        List<Car> result = carService.findAll();
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindById() {
        Car car = new Car();
        when(carRepository.findById("123")).thenReturn(car);
        assertEquals(car, carService.findById("123"));
    }

    @Test
    void testUpdate() {
        Car car = new Car();
        carService.update("123", car);
        verify(carRepository, times(1)).update("123", car);
    }

    @Test
    void testDelete() {
        carService.deleteCarById("123");
        verify(carRepository, times(1)).delete("123");
    }
}