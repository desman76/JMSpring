package web.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class CarServiceImpl implements CarService {
    static List<Car> cars;

    @PostConstruct
    public void initCarList() {
        cars = new ArrayList<>();
        cars.add(new Car("brand_1", "model_1", 200));
        cars.add(new Car("brand_2", "model_2", 220));
        cars.add(new Car("brand_3", "model_3", 180));
        cars.add(new Car("brand_4", "model_4", 250));
        cars.add(new Car("brand_5", "model_5", 300));
    }

    @Override
    public List<Car> getCars() {
        return cars;
    }
}
