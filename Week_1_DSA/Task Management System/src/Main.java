public class Main {
    public static void main(String[] args) {
        TaskManagementSystem tms = new TaskManagementSystem();

        
        tms.addTask(new Task(1, "Task 1", "Pending"));
        tms.addTask(new Task(2, "Task 2", "Completed"));
        tms.addTask(new Task(3, "Task 3", "In Progress"));

        
        System.out.println("All Tasks:");
        tms.traverseTasks();

    
        System.out.println("Searching for Task with ID 2:");
        Task task = tms.searchTask(2);
        System.out.println(task != null ? task : "Task not found");


        System.out.println("Deleting Task with ID 2:");
        boolean isDeleted = tms.deleteTask(2);
        System.out.println(isDeleted ? "Task deleted successfully" : "Task not found");

        
        System.out.println("All Tasks after deletion:");
        tms.traverseTasks();
    }
}

//STEP4:-ANALYSIS
/*
 Time Complexity of Each Operation
Add Task: O(n) - Since we need to traverse to the end of the list to add a new task.
Search Task: O(n) - In the worst case, we might need to search through the entire list.
Delete Task: O(n) - In the worst case, we might need to search through the entire list.
Traverse Tasks: O(n) - We need to visit each node in the list.
 */

 /*
Advantages of Linked Lists Over Arrays for Dynamic Data
Dynamic Size: Linked lists can easily grow and shrink in size by just changing pointers, while arrays require resizing and copying.
Efficient Insertions/Deletions: Insertions and deletions can be more efficient in linked lists as they do not require shifting elements,
 just updating pointers.

  */