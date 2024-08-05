
CREATE TABLE AuditLog (
    TransactionID NUMBER PRIMARY KEY,  
    AuditDate DATE,
    Action VARCHAR2(50),
    FOREIGN KEY (TransactionID) REFERENCES Transactions(TransactionID) 
);
CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionID, AuditDate, Action)
    VALUES (
        :NEW.TransactionID,  
        SYSDATE,
        'INSERT'
    );
END;
/
INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType) VALUES (1, 1, TO_DATE('2024-08-02', 'YYYY-MM-DD'), 200.00, 'Deposite');

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType) VALUES (2, 2, TO_DATE('2024-08-02', 'YYYY-MM-DD'), 300.00, 'Withdrawal');

SELECT * FROM AuditLog;
