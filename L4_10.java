package L4;

import java.util.ArrayList;
import java.util.List;

class Animal {
    void makeSound() {
        System.out.println("Some generic animal sound");
    }
}

class Cat extends Animal {
    @Override
    void makeSound() {
        System.out.println("Meow");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Woof");
    }
}

public class L4_10 {
    public static void printSubclassInfo(List<? extends Animal> animals) {
        for (Animal animal : animals) {
            System.out.println("Object: " + animal.getClass().getSimpleName());
            System.out.println("Sound: ");
            animal.makeSound();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Cat());
        animalList.add(new Dog());
        animalList.add(new Cat());

        printSubclassInfo(animalList);
    }
}