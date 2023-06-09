SELECT 
  EMPNO
  , ENAME
  , SAL
  , (SELECT AVG(SAL) FROM EMP) AS avg
FROM EMP
WHERE SAL > (SELECT AVG(SAL) FROM EMP);

INSERT INTO EMP_DETAIL(TEL, CHILD, AGE, EMPNO)
VALUES('01029567998', 1, 43, 7566);

SELECT * FROM EMP_DETAIL;

UPDATE EMP_DETAIL 
SET TEL='01029497998'
WHERE EMPNO = 7654;

-- DELETE FROM EMP 
-- WHERE (조건)

BEGIN 
	dbms_output.put_line('hello');
END;

DECLARE
  V_DEPTNO DEPT.DEPTNO%TYPE := 50;
BEGIN
	DBMS_OUTPUT.PUT_LINE('V_DEPTNO :' || V_DEPTNO);
END;

DECLARE
  V_DEPT_ROW DEPT%ROWTYPE;
BEGIN
	SELECT DEPTNO, DNAME, LOC INTO V_DEPT_ROW
	FROM DEPT
	WHERE DEPTNO = 40;
    DBMS_OUTPUT.PUT_LINE('DEPTNO: ' || V_DEPT_ROW.DEPTNO);
    DBMS_OUTPUT.PUT_LINE('DNAME: ' || V_DEPT_ROW.DNAME);
    DBMS_OUTPUT.PUT_LINE('LOC: ' || V_DEPT_ROW.LOC);
END;

DECLARE
  V_SCORE NUMBER := 87;
BEGIN
	IF V_SCORE >= 90 THEN
	   DBMS_OUTPUT.PUT_LINE('A학점');
	ELSIF V_SCORE >= 80 THEN
	   DBMS_OUTPUT.PUT_LINE('B학점');
	ELSIF V_SCORE >= 70 THEN
	   DBMS_OUTPUT.PUT_LINE('C학점');  
	ELSIF V_SCORE >= 60 THEN
	   DBMS_OUTPUT.PUT_LINE('D학점');	  
	ELSE
	   DBMS_OUTPUT.PUT_LINE('F학점');	
	END IF;
END;


