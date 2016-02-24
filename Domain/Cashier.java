package shop.management.system.domain;

import java.io.Serializable;

/**
 * Cashier Class 收银员类
 * 
 * @author Hang Gao
 * 
 */
public class Cashier implements Serializable
{
	private String userName;
	private String password;

	public Cashier()
	{

	}

	public Cashier(String userName, String password)
	{
		super();
		this.userName = userName;
		this.password = password;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getUserName()
	{
		return userName;
	}

	public String getPassword()
	{
		return password;
	}
}
