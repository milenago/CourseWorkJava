import javax.swing.*;
import java.util.*;

public class Director {
    private ArrayList<Employee> employees;
    private final Scanner sc = new Scanner(System.in);

    public Director(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    /** Метод найма новых работников **/
    public void hireNewEmployee(String input, JTextArea textArea1) {
        String[] arrInput = input.split(",\\s?");
        try {
            this.employees.add(new Employee(arrInput[0], Integer.parseInt(arrInput[1]), Integer.parseInt(arrInput[2])));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            textArea1.append("\nНеправильный ввод! Попробуйте снова!\n");
        }
    }

    /** Метод поиска средней зарплаты среди всех сотрудников **/
    private int findAverageSalary(ArrayList<Employee> employees) {
        int sum = 0;
        for (Employee employee : employees) {
            sum += employee.getSalary();
        }
        return sum/employees.size();
    }

    /** Метод поиска средней производительности среди всех сотрудников **/
    private int findAverageEfficiency(ArrayList<Employee> employees) {
        int sum = 0;
        for (Employee employee : employees) {
            sum += employee.getEfficiency();
        }
        return sum/employees.size();
    }

    /** Метод, предоставляющий возможность уволить всех "плохих" сотрудников
     * (у которых з/п выше средней, но производительность ниже средней) **/
    public void dismissBadEmployees(JTextArea textArea) {
        int averageSalary = findAverageSalary(employees);
        int averageEfficiency = findAverageEfficiency(employees);

        ArrayList<Employee> badEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getSalary() > averageSalary && employee.getEfficiency() < averageEfficiency)
                badEmployees.add(employee);
        }

        for (Employee badEmployee : badEmployees) {
            employees.remove(badEmployee);
        }

        textArea.append("\nУволены:\n");
        for (Employee badEmployee : badEmployees) {
            textArea.append(badEmployee.toString());
        }
    }

    /** Метод поиска сотрудника по зарплате **/
    private Employee findBySalary(int salary) {
        Employee emp = null;
        for (Employee employee : employees) {
            if (employee.getSalary() == salary)
                emp = employee;
        }
        return emp;
    }

    /** Метод сортировки и отображения сотрудников по их зарплате **/
    public ArrayList<Employee> sortBySalary(ArrayList<Employee> employees) {
        ArrayList<Employee> sortedEmployees = new ArrayList<>(); //Сюда будем добавлять отсортированных по зп работников
        int[] salaries = new int[employees.size()]; //Массив для сортировки всех зарплат

        // Записываем все зарплаты всех работников в массив
        for (int i = 0; i < employees.size(); i++) {
            salaries[i] += (employees.get(i).getSalary());
        }
        Arrays.sort(salaries); //Сортируем полученный массив по возрастанию

        // Добавляем работников в список
        for (int i = 0; i < employees.size(); i++) {
            sortedEmployees.add(findBySalary(salaries[i]));
            employees.remove(findBySalary(salaries[i]));
        }
        sortedEmployees.addAll(employees);
        employees.removeAll(employees);   // Удаляем всех работников из прошлого списка
        employees.addAll(sortedEmployees);//и заполняем его работниками из сортированного списка
        return employees;
    }

    /** Метод поиска сотрудника по производительности **/
    private Employee findByEfficiency(int efficiency) {
        for (Employee employee : employees) {
            if (employee.getEfficiency() == efficiency)
                return employee;
        }
        return null;
    }

    /** Метод сортировки и отображения сотрудников по их производительности **/
    public ArrayList<Employee> sortByEfficiency(ArrayList<Employee> employees) {
        ArrayList<Employee> sortedEmployees = new ArrayList<>(); // Сюда будем добавлять
        //отсортированных по производительности работников
        int[] efficiencies = new int[employees.size()]; //Массив для сортировки производительности всех сотрудников

        // Записываем все оценки производительности всех работников в массив
        for (int i = 0; i < employees.size(); i++) {
            efficiencies[i] += (employees.get(i).getEfficiency());
        }
        Arrays.sort(efficiencies); //Сортируем полученный массив по возрастанию

        // Добавляем работников в список
        for (int i = 0; i < employees.size(); i++) {
            sortedEmployees.add(findByEfficiency(efficiencies[i]));
            employees.remove(findByEfficiency(efficiencies[i]));
        }

        sortedEmployees.addAll(employees);
        employees.removeAll(employees);   // Удаляем всех работников из прошлого списка
        employees.addAll(sortedEmployees);//и заполняем его работниками из сортированного списка
        return employees;
    }
}
