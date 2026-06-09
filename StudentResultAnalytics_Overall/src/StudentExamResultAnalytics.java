import java.util.*;
import java.util.stream.Collectors;

public class StudentExamResultAnalytics {

    public static void main(String[] args) {

        // 1. Store students in List
        List<Student> students = Arrays.asList(
                new Student(101, "Anu", "Java", 82, true),
                new Student(102, "Bala", "Java", 45, false),
                new Student(103, "Charan", "Python", 91, true),
                new Student(104, "Divya", "Java", 67, true),
                new Student(105, "Esha", "Python", 38, false),
                new Student(106, "Farhan", "DevOps", 74, true),
                new Student(107, "Gokul", "DevOps", 88, true),
                new Student(108, "Hari", "Java", 53, true),
                new Student(109, "Isha", "Python", 79, true),
                new Student(110, "John", "DevOps", 62, true),
                new Student(111, "Kavya", "Java", 95, true),
                new Student(112, "Lokesh", "Python", 49, false)
        );

        // 2. Extract unique courses using Set
        Set<String> uniqueCourses = students.stream()
                .map(Student::getCourse)
                .collect(Collectors.toSet());

        System.out.println("Unique Courses (Set)");
        System.out.println(uniqueCourses);

        // 3. Group students by course using Map
        Map<String, List<String>> groupedStudents = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getCourse,
                        Collectors.mapping(Student::getStudentName, Collectors.toList())
                ));

        System.out.println("\nStudents Grouped by Course (Map)");
        groupedStudents.forEach((course, names) ->
                System.out.println(course + " -> " + names));

        // 4. Filter passed students using Streams + Lambda
        List<Student> passedStudents = students.stream()
                .filter(s -> s.isPassed())
                .collect(Collectors.toList());

        System.out.println("\nPassed Students (Filtered)");
        passedStudents.forEach(s ->
                System.out.print(s.getStudentName() + " "));
        System.out.println();

        // 5. Calculate total, average, highest marks
        int totalMarks = passedStudents.stream()
                .mapToInt(Student::getMarks)
                .sum();

        double averageMarks = passedStudents.stream()
                .mapToInt(Student::getMarks)
                .average()
                .orElse(0);

        Optional<Student> highestScorer = passedStudents.stream()
                .max(Comparator.comparing(Student::getMarks));

        System.out.println("\nTotal Marks of Passed Students");
        System.out.println(totalMarks);

        System.out.println("\nAverage Marks of Passed Students");
        System.out.printf("%.1f%n", averageMarks);

        System.out.println("\nHighest Marks Scorer");
        highestScorer.ifPresent(s ->
                System.out.println(s.getStudentName() + " - " + s.getMarks()));

        // 6. Sort passed students
        List<Student> sortedPassedStudents = passedStudents.stream()
                .sorted(
                        Comparator.comparing(Student::getMarks).reversed()
                                .thenComparing(Student::getStudentName)
                )
                .collect(Collectors.toList());

        System.out.println("\nSorted Passed Students (Marks ↓, Name ↑)");
        sortedPassedStudents.forEach(System.out::println);

        // 7. Course-wise total marks report
        Map<String, Integer> courseWiseMarks = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getCourse,
                        Collectors.summingInt(Student::getMarks)
                ));

        System.out.println("\nCourse-wise Total Marks (Map)");
        courseWiseMarks.forEach((course, total) ->
                System.out.println(course + " -> " + total));

        // 8. Use Optional to find student with ID 110
        Optional<Student> student110 = students.stream()
                .filter(s -> s.getStudentId() == 110)
                .findFirst();

        System.out.println("\nOptional Search Result (studentId = 110)");
        student110.ifPresentOrElse(
                s -> System.out.println("Student found: "
                        + s.getStudentName() + " - "
                        + s.getCourse() + " - "
                        + s.getMarks()),
                () -> System.out.println("Student not found")
        );

        // 9. Functional interface filter for marks >= 75
        StudentFilter highScoreFilter = s -> s.getMarks() >= 75;

        System.out.println("\nStudents with Marks >= 75");
        students.stream()
                .filter(highScoreFilter::filter)
                .forEach(System.out::println);

        // 10. Method reference to print final sorted result
        System.out.println("\nFinal Report Printed Using Method Reference");
        sortedPassedStudents.forEach(System.out::println);
    }
}