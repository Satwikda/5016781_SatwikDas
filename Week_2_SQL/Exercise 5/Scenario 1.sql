
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    
    :NEW.LastModified := SYSDATE;
END;
/

UPDATE Customers
SET Name = 'John Doe'
WHERE CustomerID = 1;

SELECT * FROM CUSTOMERS;





