package com.uni.gc;

import javax.ejb.Stateless;

import com.uni.gc.ejb.IUserLogic;

@Stateless(name="UserEJB")
public class UserEJB implements IUserLogic {

	@Override
	public UserDTO searchUser(String email) throws Exception {
		UserDTO userFound = null;
		
		if (email.equals("pakito@pakito.com")) {
			userFound = new UserDTO();
			
			userFound.setId(3);
			userFound.setName("Pakito");
			userFound.setSurname("Chokolatero");
			userFound.setEmail("pakito@pakito.com");
		}
		
		return userFound;
	}
	
	@Override
	public UserDTO searchUserLogin(String username, String email) throws Exception {
		UserDTO userFound = null;
		
		try {
			if (username.equals("Pakito") && email.equals("57925234")) {
				userFound = new UserDTO();
				
				userFound.setId(3);
				userFound.setName("Pakito");
				userFound.setSurname("Chokolatero");
				userFound.setEmail("pakito@pakito.com");
			}
			
			return userFound;
		} catch(Exception ex) {
			throw ex;
		}
	}
}
