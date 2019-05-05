package Service;

import java.sql.SQLException;

import Bean.User;
import Dao.UserDao;

public class UserService {
	public boolean register(String email,String name,String password){
		UserDao userDao=new UserDao();
		boolean register=false;
		boolean chechUser=userDao.checkUser(email);
		System.out.println("chechUser:"+chechUser);
		if (chechUser) {
			register=true;
		}
		else{
			 register=userDao.register(email, name, password);
		}
		System.out.println("register:"+register);
		return register;
	}
	public User Login(String email,String password) throws SQLException{
		UserDao userDao=new UserDao();
		User user=userDao.login( email, password);
		return user;
	
	
	

}}

