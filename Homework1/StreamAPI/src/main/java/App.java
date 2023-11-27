import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 0.1. Посмотреть разные статьи на Хабр.ру про Stream API
 * 0.2. Посмотреть видеоролики на YouTube.com Тагира Валеева про Stream API
 * <p>
 * 1. Создать список из 1_000 рандомных чисел от 1 до 1_000_000
 * 1.1 Найти максимальное
 * 2.2 Все числа, большие, чем 500_000, умножить на 5, отнять от них 150 и просуммировать
 * 2.3 Найти количество чисел, квадрат которых меньше, чем 100_000
 * <p>
 * 2. Создать класс Employee (Сотрудник) с полями: String name, int age, double salary, String department
 * 2.1 Создать список из 10-20 сотрудников
 * 2.2 Вывести список всех различных отделов (department) по списку сотрудников
 * 2.3 Всем сотрудникам, чья зарплата меньше 10_000, повысить зарплату на 20%
 * 2.4 * Из списка сотрудников с помощью стрима создать Map<String, List<Employee>> с отделами и сотрудниками внутри отдела
 * 2.5 * Из списока сорудников с помощью стрима создать Map<String, Double> с отделами и средней зарплатой внутри отдела
 */

public class App {

    public static void main(String[] args) {

        //1

        Random random = new Random();

        List<Integer> list = (IntStream.range(0, 100001)
                .mapToObj(i -> random.nextInt(100001)))
                .toList();

        System.out.println("Задание 1");
        System.out.println("\n1.1 Максимальное число: "
                + list.stream()
                .max(Integer::compare)
                .get());

        System.out.println("\n1.2 Сумма всех числа, большие, чем 50000, умноженны на 5, от них отнято 150: " +
                list.stream()
                        .filter(i -> i > 50000)
                        .map(i -> i * 5 - 150)
                        .mapToInt(a -> a)
                        .sum());

        System.out.println("\n1.3 Количество чисел, квадрат которых меньше, чем 10000: " +
                list.stream()
                        .map(i -> i * i)
                        .filter(i -> i < 10000)
                        .count());

        System.out.println();

        //2
        System.out.println("Задание 2");
        List<Employee> employees = IntStream.range(0, 10)
                .mapToObj(i -> new Employee())
                .toList();

        System.out.println("\n2.1 Список сотрудников:");
        employees.forEach(System.out::println);

        System.out.println("\n2.2 Список отелов по списку сотрудников:");
        employees.stream()
                .map(i -> i.getDepartment())
                .distinct().
                forEach(System.out::println);

        employees.stream().
                filter(employee -> employee.getSalary() < 2500.0)
                .forEach(employee -> employee.setSalary(
                        Math.round( employee.getSalary()*120.0) / 100.0));

        System.out.println("\n2.3 Список сотрудников, но люди с зп меньше 2500 получили надбавку:");
        employees.forEach(System.out::println);


    }
}
