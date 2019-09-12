package com.uni.gc;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import javax.xml.bind.DatatypeConverter;

import com.uni.gc.ejb.ITokenLogic;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Stateless(name="TokenEJB")
public class TokenEJB implements ITokenLogic {

	@Override
	public String generateToken() {
		Integer millisecondsTimeToLiveToken = 30000;
		String superBigSecretKey = "12345";
		
		//The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

	    //We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(superBigSecretKey);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		
		
	    String generatedToken =	Jwts
									.builder()
									.setId("Unigest Project")
						            .setIssuedAt(new Date())
						            .setExpiration(new Date(System.currentTimeMillis() + millisecondsTimeToLiveToken))
						            .setSubject("Tomy")
						            .setIssuer("Manuel Tomás Ortega Sánchez")
						            .signWith(signatureAlgorithm, signingKey)
						            .compact();
		
		System.out.println("Generated Token: " + generatedToken);
		
		return generatedToken;
	}
	
	@Override
	public boolean verifyToken(String tokenToVerify) {
		boolean validToken = true;
		
		try {
			
			String superBigSecretKey = "12345";
			
			//This line will throw an exception if it is not a signed JWS (as expected)
		    Claims claims = Jwts.parser()         
						       .setSigningKey(DatatypeConverter.parseBase64Binary(superBigSecretKey))
						       .parseClaimsJws(tokenToVerify)
						       .getBody();
			 
			 
			 System.out.println("TOKEN Id: " + claims.getId());
			 System.out.println("TOKEN Subject: " + claims.getSubject());
			 System.out.println("TOKEN Issuer: " + claims.getIssuer());
			 System.out.println("TOKEN Expiration: " + claims.getExpiration());
		} catch(Exception ex) {
			ex.printStackTrace();
			validToken = false;
		}
		
		return validToken;
	}
}
