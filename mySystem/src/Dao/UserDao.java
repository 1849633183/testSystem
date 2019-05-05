package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Hibernate;
import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import Bean.User;
import Utils.Hib;
import Utils.Util;

public class UserDao{
	Util util = new Util();

	public boolean checkUser(String email) {
		try{PreparedStatement preparedStatement = util.getConnection().prepareStatement("select * from users where email=?");
//		preparedStatement=util.getConnection().prepareStatement("select email from users");
		preparedStatement.setString(1, email);
		ResultSet resultSet = preparedStatement.executeQuery();
	/*	while(resultSet.next())
		{
			System.out.println(resultSet.getString(1));
		}
		return true;*/
		if (resultSet.next()) {
			System.out.println("结果集不为空!");
			return true;
		} else {
			System.out.println("结果集为空");
			return false;
		}
	}
	catch(SQLException exception){
		exception.printStackTrace();
		return false;
		
	}}
	public boolean register(String email,String name,String password){
		try{PreparedStatement preparedStatement= util.getConnection().prepareStatement("insert into users values(null,?,?,?)");
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, password);
		preparedStatement.setString(3, email);
		preparedStatement.executeUpdate();
		/*Hib hib=new Hib();
		hib.add(name, password, email);*/
		/*PreparedStatement*/ preparedStatement = util.getConnection().prepareStatement("select * from users where email=?");
		preparedStatement.setString(1, email);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return true;
		} else {
			return false;
		}
		

		
		
		
	}
	catch(SQLException exception){
		exception.printStackTrace();
		return false;
		
	}
		
	}

	public User login(String email, String password) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement= util.getConnection().prepareStatement("select id,name from users where email=? and password=?");
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, password);
		ResultSet resultSet = preparedStatement.executeQuery();
		User user=new User();
		while(resultSet.next()){
			System.out.println("id:"+resultSet.getInt("id")+"   "+"name:"+resultSet.getString("name"));
			user.setId(resultSet.getInt("id"));
			user.setName(resultSet.getString("name"));
			user.setEmail(email);
			user.setPassword(password);
			
			}
		return user;
		}
		
		
		
	
}
