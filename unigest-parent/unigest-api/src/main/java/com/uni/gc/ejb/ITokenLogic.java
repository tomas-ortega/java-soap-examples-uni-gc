package com.uni.gc.ejb;

public interface ITokenLogic {
	public String generateToken();
	public boolean verifyToken(String tokenToVerify) throws Exception;
}
