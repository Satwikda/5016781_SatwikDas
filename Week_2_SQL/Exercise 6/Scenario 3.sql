

DECLARE
    
    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID, InterestRate
        FROM Loans;
    
    v_Loan UpdateLoanInterestRates%ROWTYPE;
    
    v_NewInterestRate NUMBER := 0.5;

BEGIN
   
    OPEN UpdateLoanInterestRates;
   
    LOOP
        FETCH UpdateLoanInterestRates INTO v_Loan;
        EXIT WHEN UpdateLoanInterestRates%NOTFOUND;

        UPDATE Loans
        SET InterestRate = InterestRate + v_NewInterestRate
        WHERE LoanID = v_Loan.LoanID;

 
        DBMS_OUTPUT.PUT_LINE('Updated Loan ID: ' || v_Loan.LoanID || 
                             ', New Interest Rate: ' || (v_Loan.InterestRate + v_NewInterestRate));
    END LOOP;

    CLOSE UpdateLoanInterestRates;
 
    COMMIT;

EXCEPTION
    WHEN OTHERS THEN

        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/
