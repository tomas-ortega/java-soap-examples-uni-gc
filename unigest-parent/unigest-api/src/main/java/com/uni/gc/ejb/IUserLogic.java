package com.uni.gc.ejb;

import com.uni.gc.UserDTO;

public interface IUserLogic {
	public UserDTO searchUser(String email) throws Exception;
}
