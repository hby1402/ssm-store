package com.hby.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hby.mapper.UserMapper;
import com.hby.pojo.User;
import com.hby.pojo.UserExample;
import com.hby.pojo.UserExample.Criteria;
import com.hby.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User login(User user) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andLoginNameEqualTo(user.getLoginName());
		criteria.andPasswordEqualTo(user.getPassword());
		List<User> list = userMapper.selectByExample(example);
		
		//用户为唯一值，所以取list的第一个对象
		if(list!=null && list.size()>0) {
			return list.get(0);
		}
		
		return null;
	}
	
	
	@Override
	public void createUser(User user) {
		
		userMapper.insert(user);
	}

}
