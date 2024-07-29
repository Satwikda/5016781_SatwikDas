//STEP1:-
/*
Understand Recursive Algorithms
Concept of Recursion:
Recursion is a programming technique where a method calls itself to solve a problem.
 It simplifies problems by breaking them down into smaller, more manageable sub-problems.
  Recursive algorithms are particularly useful for problems that can be divided into similar sub-problems of the same type.
 */

 //STEP2:-
public class FinancialForecasting {

    public static double predictFutureValue(double initialValue, double growthRate, int periods) {
        if (periods == 0) {
            return initialValue;
        }
        return predictFutureValue(initialValue, growthRate, periods - 1) * (1 + growthRate);
    }

    public static void main(String[] args) {
        double initialValue = 1000.0;
        double growthRate = 0.05; 
        int periods = 10; 

        double futureValue = predictFutureValue(initialValue, growthRate, periods);
        System.out.println("Future value after " + periods + " periods: " + futureValue);
    }
}

//STEP2:-ANALYSIS
/*
 A)
Time Complexity: The time complexity of this recursive algorithm is O(n),
 where n is the number of periods. This is because the method makes a single recursive call for each period.
 ------------------------------------------------------------------------------------------------------------------
 B)Optimization: To optimize the recursive solution and avoid excessive computation,
  we can use memoization to store intermediate results and avoid redundant calculations. 
  However, for this simple problem, memoization might be overkill. For larger problems,
   dynamic programming techniques would be more appropriate.
 */
