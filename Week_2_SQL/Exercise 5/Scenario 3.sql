

CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    
    SELECT Balance INTO v_balance
    FROM Accounts
    WHERE AccountID = :NEW.AccountID;
    
    
    IF :NEW.TransactionType = 'Withdrawal' THEN
       
        IF :NEW.Amount > v_balance THEN
            RAISE_APPLICATION_ERROR(-20001, 'Withdrawal amount exceeds the current balance.');
        END IF;

 
    ELSIF :NEW.TransactionType = 'Deposit' THEN

        IF :NEW.Amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20002, 'Deposit amount must be positive.');
        END IF;

    ELSE

        RAISE_APPLICATION_ERROR(-20003, 'Invalid transaction type. Must be DEPOSIT or WITHDRAWAL.');
    END IF;
END;
/

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType) VALUES (1, 1, TO_DATE('2024-08-02', 'YYYY-MM-DD'),300.00, 'WITHDRAWAL')
