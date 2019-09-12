package com.uni.gc.configuration;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class LDAPClient {
	public static void main(String args[]) throws Exception {
		final String ldapAdServer = "ldap://ldap.forumsys.com:389";
        final String ldapSearchBase = "cn=read-only-admin,dc=example,dc=com";
        
        final String ldapUsername = "uid=einstein,DC=example,DC=com";
        final String ldapPassword = "password";
        
        //1ª Prueba
        //DirContext ldapContext = LDAPClient.loginLDAP(ldapAdServer, ldapUsername, ldapPassword);
        //LDAPClient.closeConnectionToLDAP(ldapContext);
        
        //2ª Prueba
        LDAPClient.search(ldapAdServer, ldapUsername, ldapPassword);
        System.out.println("CONNECTION OK!");
	}
	

	public static DirContext loginLDAP(String url, String user, String password) throws NamingException{
	  Hashtable<String, String> env = new Hashtable<String, String>();
	  env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	  env.put(Context.PROVIDER_URL, url);
	  env.put(Context.SECURITY_AUTHENTICATION,"simple");
	  env.put(Context.SECURITY_PRINCIPAL, user);
	  env.put(Context.SECURITY_CREDENTIALS,password);
	  
	  //Conseguimos contexto de conexion
	  DirContext ctx = new InitialDirContext(env);
	  return ctx;
	}
	
	public static void search (String url, String user, String password) throws NamingException{ 
	    //Recordar que el username tiene que tener toda la ruta de OUs/DCs/CNs
	    DirContext context = LDAPClient.loginLDAP(url, user, password);
	    SearchControls ctls = new SearchControls();
	    ctls. setReturningObjFlag (true); // Para que devuelva los elementos que buscamos
	    
	    String returning[] = new String[2];
	    returning[0] = "cn";
	    returning[1] = "sn";
	    
	    //Asignamos los atributos a devolver
	    ctls.setReturningAttributes(returning);
	    ctls.setSearchScope(SearchControls.OBJECT_SCOPE);
	    
	    String search = user; // Search es "en donde buscar" de los directorios del servidor
	    //String filter = "ou=" + "scientists"; // filtro trivial
	    String filter = "uid=einstein";
	 
	    NamingEnumeration answer = context.search(search, filter, ctls);
	    
	    while(answer.hasMoreElements()) {
	    	SearchResult result = (SearchResult) answer.next();
	    	String attributeCN = result.getAttributes().get("cn").get().toString();
	    	String attributeSN = result.getAttributes().get("sn").get().toString();
	    	
	    	System.out.println("CN: " + attributeCN);
	    	System.out.println("SN: " + attributeSN);
	    }
	    	    
	    LDAPClient.closeConnectionToLDAP(context);
	 }
	
	
	
	public static void closeConnectionToLDAP(DirContext ctx) {
	    try {
	        ctx.close();
	    } catch (NamingException e) {
	        // No se habia podido conectar, ya se habia cerrado la conexion, etc..
	        e.printStackTrace();
	    }
	}

}