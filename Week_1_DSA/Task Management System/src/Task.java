//STEP1:-
/*
 Understand Linked Lists
Singly Linked List:-
A Singly Linked List is a data structure consisting of a collection of nodes where each node contains data and a reference 
(or link) to the next node in the sequence.

Doubly Linked List:-
A Doubly Linked List is similar to a Singly Linked List but each node contains an additional reference to the previous node, 
allowing traversal in both directions.
 */


class Task {
    int taskId;
    String taskName;
    String status;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
