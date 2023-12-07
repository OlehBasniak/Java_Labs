package L3;

import java.util.*;
import java.util.stream.*;
class Student {
    private String name;
    private int grade;
    private int course;

    public Student(String name, int grade, int course) {
        this.name = name;
        this.grade = grade;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public int getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return name + ", " + "оціка - " + grade + "; " + course + " курс";
    }
}

public class L3_3 {

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Анна", 85, 1),
                new Student("Богдан", 92, 1),
                new Student("Віктор", 76, 2),
                new Student("Ганна", 89, 2),
                new Student("Денис", 95, 3),
                new Student("Євген", 68, 3)
        );

        System.out.println("Початковий список студентів:");
        students.forEach(System.out::println);

        Map<Integer, Double> averageGradesByCourse = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getCourse,
                        Collectors.averagingInt(Student::getGrade)
                ));

        System.out.println("\nСередній бал за курсами:");
        averageGradesByCourse.forEach((course, avgGrade) ->
                System.out.println("Курс " + course + ": " + avgGrade)
        );
    }
}
