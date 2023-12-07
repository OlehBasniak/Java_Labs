package L3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class L3_8 {

    // Клас для представлення міста з температурою
    static class City {
        String name;
        double temperature;

        public City(String name, double temperature) {
            this.name = name;
            this.temperature = temperature;
        }

        public String getName() {
            return name;
        }

        public double getTemperature() {
            return temperature;
        }
    }

    public static void main(String[] args) {
        // Створення списку міст
        List<City> cities = new ArrayList<>();
        cities.add(new City("Київ", 32.5));
        cities.add(new City("Львів", 25.0));
        cities.add(new City("Одеса", 35.0));
        cities.add(new City("Харків", 28.0));
        cities.add(new City("Дніпро", 31.0));

        // Виклик функції для фільтрування міст
        List<City> coolerCities = filterAndPrintHotCities(cities);

        System.out.println(" ");
        // Вивід результату
        System.out.println("Міста з температурою менше 30°C:");
        coolerCities.forEach(city -> System.out.println(city.getName() + " - " + city.getTemperature() + "°C"));
    }

    // Функція для фільтрування міст
    public static List<City> filterAndPrintHotCities(List<City> cities) {
        return cities.stream()
                .peek(city -> {
                    if (city.getTemperature() > 30) {
                        System.out.println("Спекотне місто: " + city.getName());
                    }
                })
                .filter(city -> city.getTemperature() <= 30)
                .collect(Collectors.toList());
    }
}
