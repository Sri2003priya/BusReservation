package s1;

import java.sql.*;
public class jdbcdemo {
public static void main(String args[]) throws Exception {
	batchdemo();
	}

public static void readRecords() throws Exception{
	String url = "jdbc:mysql://localhost:3306/s1";
	String userName = "root";
	String passWord = "Sripriya28@";
	
	String query = "select * from employee";
	Connection con = DriverManager.getConnection(url,userName,passWord);
	
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery(query);
	
	while(rs.next()) {
	System.out.println("Id is " + rs.getInt(1));
	System.out.println("Name is " + rs.getString(2));
	System.out.println("Salary  is " + rs.getInt(3));
	}
	con.close();

}
public static void insertRecord() throws Exception{
	String url = "jdbc:mysql://localhost:3306/s1";
	String userName = "root";
	String passWord = "Sripriya28@";
	
	String query = "insert into employee values(4,'Bose',1200)";
	Connection con = DriverManager.getConnection(url,userName,passWord);
	
	Statement st = con.createStatement();
	int rows = st.executeUpdate(query);
	 
	System.out.println("Number of rows affected: " + rows);
	con.close();

}

public static void insertVar() throws Exception{
	String url = "jdbc:mysql://localhost:3306/s1";
	String userName = "root";
	String passWord = "Sripriya28@";
	
	int id =5;
	String name="Subash";
	int salary = 1500;
	
	String query = "insert into employee values(" + id+ ",'" + name + "'," + salary + ");";
	Connection con = DriverManager.getConnection(url,userName,passWord);
	
	Statement st = con.createStatement();
	int rows = st.executeUpdate(query);
	 
	System.out.println("Number of rows affected: " + rows);
	con.close();

}
public static void insertUsingPst() throws Exception{
	String url = "jdbc:mysql://localhost:3306/s1";
	String userName = "root";
	String passWord = "Sripriya28@";
	
	int id =6;
	String name="Priya";
	int salary = 1300;
	
	String query = "insert into employee values(?,?,?);";
	Connection con = DriverManager.getConnection(url,userName,passWord);
	
	PreparedStatement pst = con.prepareStatement(query);
	pst.setInt(1, id);
	pst.setString(2, name);
	pst.setInt(3,salary);
	
	int rows = pst.executeUpdate();
	
	System.out.println("Number of rows affected:" + rows);
	con.close();

}
public static void delete() throws Exception{
	String url = "jdbc:mysql://localhost:3306/s1";
	String userName = "root";
	String passWord = "Sripriya28@";
	
	int id =5;
	
	
	String query = "delete from employee where emp_id = " + id;
	Connection con = DriverManager.getConnection(url,userName,passWord);
	
	Statement st = con.createStatement();
	int rows = st.executeUpdate(query);
	 
	System.out.println("Number of rows affected: " + rows);
	con.close();

}
public static void update() throws Exception{
	String url = "jdbc:mysql://localhost:3306/s1";
	String userName = "root";
	String passWord = "Sripriya28@";      
	
	String query = "update employee  set salary  = 1800 where emp_id =1";
	Connection con = DriverManager.getConnection(url,userName,passWord);
	
	Statement st = con.createStatement();
	int rows = st.executeUpdate(query);
	 
	System.out.println("Number of rows affected: " + rows);
	con.close();

}
//type of statement
//Normal statement (create statement)
//prepared statement
//callable statement(stored procedure)

public static void sp() throws Exception{
	String url = "jdbc:mysql://localhost:3306/s1";
	String userName = "root";
	String passWord = "Sripriya28@";

	Connection con = DriverManager.getConnection(url,userName,passWord);
	CallableStatement cst = con.prepareCall("{call GetEmp()}");//callableStatement is a object
	ResultSet rs = cst.executeQuery();
	
	while(rs.next()) {
		System.out.println("Id is " + rs.getInt(1));
		System.out.println("Name is " + rs.getString(2));
		System.out.println("Salary  is " + rs.getInt(3));
		}
	con.close();

	}
//calling stored procedure with input parameter
public static void sp2() throws Exception{
	String url = "jdbc:mysql://localhost:3306/s1";
	String userName = "root";
	String passWord = "Sripriya28@";

	int id = 3;
	Connection con = DriverManager.getConnection(url,userName,passWord);
	CallableStatement cst = con.prepareCall("{call GetEmpById(?)}");//callableStatement is a object
	cst.setInt(1, id);
	ResultSet rs = cst.executeQuery();
	
	while(rs.next()) {
		System.out.println("Id is " + rs.getInt(1));
		System.out.println("Name is " + rs.getString(2));
		System.out.println("Salary  is " + rs.getInt(3));
		}
	con.close();

	}
//calling stored procedure with in and out parameter
public static void sp3() throws Exception{
	String url = "jdbc:mysql://localhost:3306/s1";
	String userName = "root";
	String passWord = "Sripriya28@";

	int id = 3;
	Connection con = DriverManager.getConnection(url,userName,passWord);
	CallableStatement cst = con.prepareCall("{call GetNameById(?,?)}");//callableStatement is a object
	cst.setInt(1, id);
	cst.registerOutParameter(2,Types.VARCHAR);
	ResultSet rs = cst.executeQuery();
	
	cst.executeUpdate();
	
	System.out.println(cst.getString(2));
	
	con.close();

	}
//commit vs autocommit
public static void commitdemo() throws Exception{
	String url = "jdbc:mysql://localhost:3306/s1";
	String userName = "root";
	String passWord = "Sripriya28@";

	String query1 = "update employee set salary = 2000 where emp_id = 1";
	String query2 = "update employee set salary = 2000 where emp_id = 2";
	Connection con = DriverManager.getConnection(url,userName,passWord);
	con.setAutoCommit(false);
	Statement st = con.createStatement();
	int rows1 = st.executeUpdate(query1);
	System.out.println("rows affected"+rows1);
	
	int rows2 = st.executeUpdate(query2);
	System.out.println("rows affected"+rows2);
	
//	if(rows1 >0 && rows2>0) {
//		con.commit();
//	}
//	
	con.close();

	}
public static void batchdemo() throws Exception{
	String url = "jdbc:mysql://localhost:3306/s1";
	String userName = "root";
	String passWord = "Sripriya28@";

	String query1 = "update employee set salary = 5000 where emp_id = 1";
	String query2 = "update employee set salary = 5000 where emp_id = 2";
	String query3 = "update employee set salary = 5000 where emp_id = 3";
	String query4 = "update employee set salary = 5000 where emp_id = 4";
	Connection con = DriverManager.getConnection(url,userName,passWord);
	con.setAutoCommit(false);
	Statement st = con.createStatement();
	st.addBatch(query1);
	st.addBatch(query2);
	st.addBatch(query3);
	st.addBatch(query4);
	
	int [] res= st.executeBatch();
	for(int i:res) {
//		System.out.println("Rows affected"+ i);
		if(i>0) {
			continue;
		}
		else {
			con.rollback();
		}
	}
	con.commit();
	
	con.close();

	}

}
