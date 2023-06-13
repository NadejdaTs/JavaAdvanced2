package bakery;

import java.util.ArrayList;
import java.util.List;

public class Bakery {
    private String name;
    private int capacity;
    List<Employee> employees;

    public Bakery(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
        this.employees = new ArrayList<>();
    }

    public void add(Employee employee){
        if(employees.size() < this.capacity){
            this.employees.add(employee);
        }
    }

    public boolean remove(String name){
        int index = searchedByName(name);
        if(index != -1){
            employees.remove(index);
            return true;
        }
        return false;
    }

    public Employee getOldestEmployee(){
        int max = 0;
        Employee resultEmployee = null;
        for (int i = 0; i < employees.size(); i++) {
            int currAge = employees.get(i).getAge();
            if(currAge > max){
                max = currAge;
                resultEmployee = employees.get(i);
            }
        }
        return resultEmployee;
    }

    public Employee getEmployee(String name){
        int index = searchedByName(name);
        return employees.get(index);
    }

    public int getCount(){
        return this.employees.size();
    }

    public String report(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Employees working at Bakery %s:", this.name)).append(System.lineSeparator());
        employees.stream()
                .forEach(e -> sb.append(e.toString()).append(System.lineSeparator()));

        return sb.toString().trim();
    }

    public int searchedByName(String name){
        int result = -1;
        for (int i = 0; i < employees.size(); i++) {
            if(employees.get(i).getName().equals(name)){
                result = i;
            }
        }
        return result;
    }
}
