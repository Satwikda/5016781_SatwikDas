SET SERVEROUTPUT ON;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Starting to fetch loan reminders...');

    FOR loan_record IN (
        SELECT l.LoanID, l.CustomerID, l.EndDate, c.Name
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30
    ) LOOP
        
        DBMS_OUTPUT.PUT_LINE('Reminder: Loan ID ' || loan_record.LoanID ||
                             ' for customer ' || loan_record.Name ||
                             ' (Customer ID: ' || loan_record.CustomerID || ') is due on ' ||
                             TO_CHAR(loan_record.EndDate, 'DD-MON-YYYY') || '.');
    END LOOP;

    DBMS_OUTPUT.PUT_LINE('Finished fetching loan reminders.');
END;





