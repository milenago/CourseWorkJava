import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EmployeesBase extends JFrame{
    private static ArrayList<Employee> employees;
    private JPanel panel;
    private JTextArea textArea1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JTextField textField1;

    private EmployeesBase(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public void setGUI() {
        JFrame frame = new JFrame("База сотрудников");
        frame.setSize(600,400);

        //Текстовое поле (TextBox)
        //Шрифт и табуляция
        textArea1.setFont(new Font("Dialog", Font.PLAIN, 18));
        textArea1.setTabSize(10);
        //Параметры переноса слов
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);
        textArea1.append("Привет, босс!");
        textArea1.append(Employee.printAllEmployees(employees));

        //Кнопки
        button1.setActionCommand("Нанять");
        button1.addActionListener(new ActionHandler());

        button2.setActionCommand("ОтсортироватьЗП");
        button2.addActionListener(new ActionHandler());

        button3.setActionCommand("Уволить");
        button3.addActionListener(new ActionHandler());

        button4.setActionCommand("ОтсортироватьПР");
        button4.addActionListener(new ActionHandler());

        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /** Обработка кнопок **/
    public class ActionHandler implements ActionListener {
        Director director = new Director(employees);
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Нанять")) {
                director.hireNewEmployee(getFromTextField(textField1), textArea1);
                textArea1.append(Employee.printAllEmployees(employees));
            }
            else if (e.getActionCommand().equals("ОтсортироватьЗП")) {
                employees = director.sortBySalary(employees);
                textArea1.append(Employee.printAllEmployees(employees));
            }
            else if (e.getActionCommand().equals("ОтсортироватьПР")) {
                employees = director.sortByEfficiency(employees);
                textArea1.append(Employee.printAllEmployees(employees));
            }
            else if (e.getActionCommand().equals("Уволить")) {
                director.dismissBadEmployees(textArea1);
                textArea1.append(Employee.printAllEmployees(employees));
            }
        }
    }

    /** Метод возвращающий текст из текстового поля и удаляющий все, что в нем было написано **/
    private String getFromTextField(JTextField textField) {
        String employee = textField.getText();
        textField.setText("");
        return employee;
    }

    public static void main(String[] args) {
        employees = new ArrayList<>();
        //Формируем "базу данных" сотрудников
        employees.add(new Employee("Игорь",80000,50));
        employees.add(new Employee("Дмитрий",70000,70));
        employees.add(new Employee("Екатерина",65000,75));
        employees.add(new Employee("Александр",80000,85));
        employees.add(new Employee("Мария",100000,20));

        EmployeesBase e = new EmployeesBase(employees);
        e.setGUI();
    }

}
