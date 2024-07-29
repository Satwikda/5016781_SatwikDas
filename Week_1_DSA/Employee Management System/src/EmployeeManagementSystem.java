

public class EmployeeManagementSystem {
    private Employee[] employees;
    private int count;

    public EmployeeManagementSystem(int capacity) {
        employees = new Employee[capacity];
        count = 0;
    }


    public void addEmployee(Employee employee) {
        if (count < employees.length) {
            employees[count++] = employee;
        } else {
            System.out.println("Array is full, cannot add more employees.");
        }
    }

    
    public Employee searchEmployee(int employeeId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                return employees[i];
            }
        }
        return null;
    }


    public void traverseEmployees() {
        for (int i = 0; i < count; i++) {
            System.out.println(employees[i]);
        }
    }


    public void deleteEmployee(int employeeId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
            
                for (int j = i; j < count - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--count] = null;
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem(10);

        
        ems.addEmployee(new Employee(1, "John Doe", "Manager", 50000));
        ems.addEmployee(new Employee(2, "Jane Smith", "Developer", 60000));

    
        System.out.println("All Employees:");
        ems.traverseEmployees();

    
        System.out.println("Searching for Employee with ID 1:");
        System.out.println(ems.searchEmployee(1));

    
        System.out.println("Deleting Employee with ID 1:");
        ems.deleteEmployee(1);
        ems.traverseEmployees();
    }
}



//STEP4:-ANALYSIS
/*
 1)
 Time Complexity:
Add: O(1) if there is space available in the array.
Search: O(n) where n is the number of employees.
Traverse: O(n) where n is the number of employees.
Delete: O(n) because it may require shifting elements to the left.
 */

 /*
  2) 
   Limitations of Arrays and When it's used:
=>
Arrays have a fixed size. Once the array is full, we cannot add more elements unless we create a new larger array and copy elements over.
Searching and deleting elements can be inefficient for large arrays as it involves linear time complexity.
  */