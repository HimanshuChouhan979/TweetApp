package com.tweetapp.users.repository;

import java.util.ArrayList;
import java.util.List;

import com.tweetapp.users.model.Users;
import org.springframework.stereotype.Repository;



@Repository
public class UsersDao {
	
	private List<Users> list;

	public UsersDao() {
		super();
		this.list = new ArrayList<Users>();


	}
	
	public Users findById(String id)
	{
		for(Users u:list)
		{
			if(u.getUserName().equals(id))
			{
				return u;
			}
		}
	 return null;
	}
	
	

}
