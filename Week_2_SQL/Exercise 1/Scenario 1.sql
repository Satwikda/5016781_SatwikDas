UPDATE Loans
SET InterestRate = InterestRate - 0.01
WHERE CustomerID IN (
    SELECT CustomerID
    FROM Customers
    WHERE FLOOR(MONTHS_BETWEEN(SYSDATE, DOB) / 12) > 60
);

 SELECT EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM DOB) AS age
 FROM Customers;

UPDATE Loans
SET InterestRate = InterestRate - 0.01
WHERE CustomerID IN (
    SELECT CustomerID
    FROM Customers
    WHERE EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM DOB) > 60
);
