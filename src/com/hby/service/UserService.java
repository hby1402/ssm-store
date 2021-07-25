package com.hby.service;

import com.hby.pojo.User;

public interface UserService {
	public User login(User user);
	
	public void createUser(User user);
}
