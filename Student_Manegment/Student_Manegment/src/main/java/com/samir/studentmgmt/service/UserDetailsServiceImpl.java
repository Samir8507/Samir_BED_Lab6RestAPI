package com.samir.studentmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.samir.studentmgmt.entity.User;
import com.samir.studentmgmt.repository.UserRepository;
import com.samir.studentmgmt.security.MyUserDetails;

public class UserDetailsServiceImpl implements UserDetailsService {
        @Autowired
        private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.getUserByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		return new MyUserDetails(user);
	}



}
