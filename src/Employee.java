import java.util.ArrayList;

public class Employee {
    private final String name;
    private final int salary;
    private final int efficiency;

    /** Констурктор **/
    public Employee(String name, int salary, int efficiency) {
        this.name = name;
        this.salary = salary;
        this.efficiency = efficiency;
    }

    // Геттеры
    public int getSalary() {
        return salary;
    }

    public int getEfficiency() {
        return efficiency;
    }

    /** Переопределение метода toString **/
    @Override
    public String toString() {
        return "Имя: " + name + ", З/П: " + salary + ", Производительность: " + efficiency + "\n";
    }

    /** Метод, для вывода всех работников в столбик **/
    public static String printAllEmployees(ArrayList<Employee> employees) {
        return ("\nВаши работники:\n" + employees.toString()
                .replace("[","")
                .replace("]","")
                .replace("\n, ", "\n"));
    }
}

