package application;

import entities.EmployeeEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main( String[] args ){
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<EmployeeEntity> list = new ArrayList<>();
        System.out.print("How many employees will be registred? ");
        int N = sc.nextInt();
        for(int i = 0; i < N; i++){

            System.out.println();
            System.out.println("Employee #"+ (i+1) + ": ");

            System.out.print("Id: ");
            int id = sc.nextInt();
            while (hasId(list, id)){
                System.out.println("Id already taken! Try again: ");
                id = sc.nextInt();
            }

            System.out.print("Name: ");
            sc.nextLine();
            String name = sc.nextLine();

            System.out.print("Salary: ");
            double salary = sc.nextDouble();

            EmployeeEntity employeeEntity = new EmployeeEntity(id, name, salary);

            list.add(employeeEntity);
        }

        System.out.print("Enter the employee id that will have salary increase: ");
        final int idSalary = sc.nextInt();

        EmployeeEntity employeeEntity = list.stream().filter(x -> x.getId() == idSalary).findFirst().orElse(null);

        //Integer pos = position(list, idSalary);

        if (employeeEntity == null){
            System.out.println("This id does not exist!");
        }
        else {
            System.out.print("Enter the percentage: ");
            double percent = sc.nextDouble();
            employeeEntity.increaseSalary(percent);
        }

        System.out.println();
        System.out.println("List of employees: ");
        for(EmployeeEntity employee : list){
            System.out.println(employee);
        }


        sc.close();
    }
    public static Integer position(List<EmployeeEntity> list, int id){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getId() == id ){
                return i;
            }
        }
        return null;
    }

    public static boolean hasId(List<EmployeeEntity> list, int id){
        EmployeeEntity employeeEntity = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        return employeeEntity != null;
    }
}
