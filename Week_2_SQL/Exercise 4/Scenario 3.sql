

CREATE OR REPLACE FUNCTION HasSufficientBalance (
    p_AccountID IN Accounts.AccountID%TYPE,
    p_Amount IN NUMBER
) RETURN BOOLEAN IS
    v_Balance NUMBER;
BEGIN
    
    SELECT Balance INTO v_Balance
    FROM Accounts
    WHERE AccountID = p_AccountID;

    
    IF v_Balance >= p_Amount THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
       
        RETURN FALSE;
    WHEN OTHERS THEN
        
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        RETURN FALSE;
END HasSufficientBalance;
/
DECLARE
    v_HasSufficient BOOLEAN;
BEGIN
    v_HasSufficient := HasSufficientBalance(p_AccountID => 222, p_Amount => 500);
    IF v_HasSufficient THEN
        DBMS_OUTPUT.PUT_LINE('The account has sufficient balance.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('The account does not have sufficient balance.');
    END IF;
END;
/
