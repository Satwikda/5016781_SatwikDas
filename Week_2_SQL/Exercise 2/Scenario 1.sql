

SET SERVEROUTPUT ON;
CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_from_account_id  NUMBER,
    p_to_account_id    NUMBER,
    p_amount           NUMBER
) IS
    insufficient_funds EXCEPTION;

    v_from_account_balance NUMBER;
    v_to_account_balance   NUMBER;

    v_error_message VARCHAR2(4000);

BEGIN
    
    DBMS_OUTPUT.PUT_LINE('Starting Procedure');

    
    SELECT balance INTO v_from_account_balance
    FROM Accounts
    WHERE AccountID = p_from_account_id
    FOR UPDATE;

    DBMS_OUTPUT.PUT_LINE('From Account Balance: ' || v_from_account_balance);

    IF v_from_account_balance < p_amount THEN
        RAISE insufficient_funds;
    END IF;

    
    SELECT balance INTO v_to_account_balance
    FROM Accounts
    WHERE AccountID = p_to_account_id
    FOR UPDATE;

    DBMS_OUTPUT.PUT_LINE('To Account Balance: ' || v_to_account_balance);

  
    UPDATE Accounts
    SET balance = balance - p_amount, LastModified = SYSDATE
    WHERE AccountID = p_from_account_id;

   
    UPDATE Accounts
    SET balance = balance + p_amount, LastModified = SYSDATE
    WHERE AccountID = p_to_account_id;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Transaction Committed');

EXCEPTION
    WHEN insufficient_funds THEN
        DBMS_OUTPUT.PUT_LINE('Insufficient Funds Exception');
        v_error_message := 'Insufficient funds in account ' || p_from_account_id;

        ---DBMS_OUTPUT.PUT_LINE('Logging error: ' || v_error_message);
        ---INSERT INTO error_log (error_id, error_message, log_date)
        ---VALUES (error_log_seq.NEXTVAL, v_error_message, SYSDATE);
        
        --DBMS_OUTPUT.PUT_LINE('Error logged successfully');
        
        ROLLBACK;

    WHEN OTHERS THEN
        v_error_message := SQLERRM;
        DBMS_OUTPUT.PUT_LINE('Other Exception: ' || v_error_message);

        ---DBMS_OUTPUT.PUT_LINE('Logging error: ' || v_error_message);
        ---INSERT INTO error_log (error_id, error_message, log_date)
        ---VALUES (error_log_seq.NEXTVAL, v_error_message, SYSDATE);
        
        --DBMS_OUTPUT.PUT_LINE('Error logged successfully');
        
        ROLLBACK;
END SafeTransferFunds;
/


INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (1, 'John Doe', TO_DATE('1985-05-15', 'YYYY-MM-DD'), 1000, SYSDATE);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (2, 'Jane Smith', TO_DATE('1990-07-20', 'YYYY-MM-DD'), 1500, SYSDATE);


INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (1, 1, 'Savings', 1000, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (2, 2, 'Checking', 1500, SYSDATE);

COMMIT;

SELECT * FROM ACCOUNTS;

EXEC SafeTransferFunds(1, 2, 1000);

EXEC SafeTransferFunds(1, 2, 1500);

SELECT * FROM error_log;
