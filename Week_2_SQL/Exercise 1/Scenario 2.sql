ALTER TABLE Customers
ADD IsVIP NUMBER(1) DEFAULT 0;

BEGIN
    FOR customer_record IN (
        SELECT CustomerID, Balance
        FROM Customers
    ) LOOP
        IF customer_record.Balance > 10000 THEN
            
            UPDATE Customers
            SET IsVIP = 1
            WHERE CustomerID = customer_record.CustomerID;
            
        END IF;
    END LOOP;
END;

SELECT * FROM CUSTOMERS;
