//STEP1:-
/*Understand Search Algorithms:-

A) Linear Search:
Linear search is a straightforward method for finding a particular value in a list.
 It checks each element in the list until it finds the target value.
Time Complexity: O(n), where n is the number of elements in the list
-----------------------------
B) Binary Search:
Binary search is a more efficient algorithm but requires the list to be sorted.
 It repeatedly divides the search interval in half, comparing the target value to the middle element.
Time Complexity: O(log n), where n is the number of elements in the list.
*/

//STEP2:-
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Book {
    int bookId;
    String title;
    String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

class Library {
    public static Book linearSearchByTitle(List<Book> books, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public static Book binarySearchByTitle(List<Book> books, String title) {
        Collections.sort(books, Comparator.comparing(Book::getTitle));

        int left = 0;
        int right = books.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Book midBook = books.get(mid);

            int comparison = midBook.getTitle().compareToIgnoreCase(title);

            if (comparison == 0) {
                return midBook;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald"));
        books.add(new Book(2, "Moby Dick", "Herman Melville"));
        books.add(new Book(3, "To Kill a Mockingbird", "Harper Lee"));
        books.add(new Book(4, "1984", "George Orwell"));
        books.add(new Book(5, "Pride and Prejudice", "Jane Austen"));

        System.out.println("Linear Search:");
        System.out.println(linearSearchByTitle(books, "1984"));

        System.out.println("Binary Search:");
        System.out.println(binarySearchByTitle(books, "1984"));
    }
}

//STEP4:-Analysis
/*
 A) Time Complexity:=
Linear Search: O(n)
Binary Search: O(log n) (requires a sorted list)
---------------------------------------------------
B)When to use each algorithm:

Linear Search: Suitable for small or unsorted lists. It is simple to implement and doesn't require preprocessing.

Binary Search: Ideal for large, sorted lists due to its logarithmic time complexity, 
making it much faster than linear search for large datasets.
 */