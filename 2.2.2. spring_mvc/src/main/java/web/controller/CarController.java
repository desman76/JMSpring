package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;

import java.util.List;

@Controller
public class CarController {

    private CarService carService;

    @RequestMapping("/car")
    public String getCars(@RequestParam(value = "count", required = false) Integer count, Model model) {
        List<Car> cars = carService.getCars();
        if (count == null || count >= cars.size()) count = cars.size();

        List<Car> result = cars.subList(0, count);

        model.addAttribute("cars", result);
        System.out.println(count);
        return "cars";
    }

    @Autowired
    public void setCarService(CarService service) {
        carService = service;
    }
}
