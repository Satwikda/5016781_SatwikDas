CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest (
    p_InterestRate IN NUMBER
) IS
BEGIN
    
    UPDATE Accounts
    SET Balance = Balance * (1 + p_InterestRate / 100)
    WHERE AccountType = 'Savings';
    
    
    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('Monthly interest applied to all savings accounts with an interest rate of ' || p_InterestRate || '%.');
EXCEPTION
    WHEN OTHERS THEN
       
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        ROLLBACK;
END ProcessMonthlyInterest;
/
BEGIN
    ProcessMonthlyInterest(p_InterestRate => 1);
END;
/
SELECT * FROM ACCOUNTS;
