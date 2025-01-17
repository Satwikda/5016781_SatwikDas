CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_Department IN Employees.Department%TYPE,
    p_BonusPercentage IN NUMBER
) IS
BEGIN
    
    UPDATE Employees
    SET Salary = Salary * (1 + p_BonusPercentage / 100)
    WHERE Department = p_Department;
    
   
    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('Salaries updated with a bonus of ' || p_BonusPercentage || '% for department ' || p_Department || '.');
EXCEPTION
    WHEN OTHERS THEN
       
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        ROLLBACK; 
END UpdateEmployeeBonus;
/

SELECT * FROM EMPLOYEES;

BEGIN
    UpdateEmployeeBonus(p_Department => 'Sales', p_BonusPercentage => 10);
END;
/

SELECT * FROM EMPLOYEES;

