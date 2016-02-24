package shop.management.system.model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import shop.management.system.domain.Cashier;
import shop.management.system.exception.CashierHasExit;
import shop.management.system.exception.CashierNotFound;

/**
 * Class managementOperation 商品管理操作类
 * 
 * @author Hang Gao
 *
 */
public class ManagementOperation
{
	private List<Cashier> list;
	File file;

	public ManagementOperation()
	{

	}

	public List<Cashier> getList()
	{
		return list;
	}

	/**
	 * Create and add Cashier Objects 创建收银员对象
	 * 
	 * @param userName
	 *            : Cashier userName 收银员的登录用户名
	 * @param password
	 *            : Cashier password 收银员的登录密码
	 * @param againPassword
	 *            : Cashier password double check 收银员的登录密码验证
	 */
	public void addCashier(String userName, String password, String againPassword)
	{
		Cashier cashier = null;

		if (password.equals(againPassword))
		{
			cashier = new Cashier(userName, password);
			list.add(cashier);
			write(list);
		} else
		{
			System.out.println("Failed: Two Passwords Do Not Match");
		}
	}

	/**
	 * Delete Cashier 删除收银员对象
	 * 
	 * @param userName
	 *            : Cashier username 收银员的用户名
	 */
	public void deleteCashier(int index)
	{
		list = read();
		list.remove(index);
		write(list);
	}

	/**
	 * Update Cashier username 更改收银员的账号
	 * 
	 * @param cashier
	 *            收银员对象
	 * @param userName
	 *            收银员的账号
	 */
	public void updateUserName(Cashier cashier, String userName)
	{
		cashier.setUserName(userName);
		write(list);
	}

	/**
	 * Update Cashier Password 更改收银员的账号密码
	 * 
	 * @param cashier
	 *            收银员对象
	 * @param password
	 *            收银员账号的密码
	 */
	public void updatePassword(Cashier cashier, String password)
	{
		cashier.setPassword(password);
		write(list);
	}

	/**
	 * 显示所有注册的账户名称
	 */
	public List<Cashier> displayAllCashier()
	{
		list = read();
		return list;
	}

	/**
	 * 收银员登录
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @return 登录成功返回true，否则返回false
	 */
	public boolean logIn(String userName, String password)
	{
		list = read();
		for (Cashier ca : list)
		{
			if (userName.equals(ca.getUserName()) && password.equals(ca.getPassword()))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Search Cashier username 查找收银员的用户名
	 * 
	 * @param userName
	 *            用户名
	 * @return : Cashier 收银员对象
	 */
	public Cashier check(String userName)
	{
		Cashier cashier = null;
		list = read();
		for (Cashier ca : list)
		{
			if (userName.equals(ca.getUserName()))
			{
				cashier = ca;
				return cashier;
			}
		}
		return cashier;
	}

	/**
	 * Search Cashier ID 查找收银员的用户名
	 * 
	 * @param userName
	 *            用户名
	 * @return : Index in the Cashier list 该用户名在list中索引
	 */
	public int checkIndex(String userName)
	{
		int index = -1;
		list = read();
		for (Cashier ca : list)
		{
			if (userName.equals(ca.getUserName()))
			{
				index = list.indexOf(ca);
				return index;
			}
		}
		return index;
	}

	/**
	 * Tell a username exists or not 判断该用户名是否存在
	 * 
	 * @param userName
	 *            用户名
	 * @return exists: true，not: false
	 */
	private boolean isExist(String userName)
	{
		list = read();
		for (Cashier ca : list)
		{
			if (userName.equals(ca.getUserName()))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Read Cashier List from cashier.dat 读取收记录收银员的文件
	 * 
	 * @return
	 */
	public List<Cashier> read()
	{

		InputStream is = null;
		ObjectInputStream ois = null;

		try
		{
			if (file == null)
			{
				file = new File("cashier.dat");
				file.createNewFile();
			}
			is = new FileInputStream(file);
			ois = new ObjectInputStream(is);

			list = (List<Cashier>) ois.readObject();

		} catch (EOFException e)
		{
			list = new ArrayList<Cashier>();
			return list;
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (is != null)
				{
					is.close();
				}

				if (ois != null)
				{
					ois.close();
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		return list;
	}

	/**
	 * Write cashier list into cashier.dat 写入记录收银员的文件
	 * 
	 * @param list
	 *            收银员的list集合
	 */
	public void write(List<Cashier> list)
	{
		OutputStream os = null;
		ObjectOutputStream oos = null;

		try
		{
			if (file == null)
			{
				file = new File("cashier.dat");
				file.createNewFile();
			}

			os = new FileOutputStream(file);
			oos = new ObjectOutputStream(os);

			oos.writeObject(list);
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (os != null)
				{
					os.close();
				}

				if (oos != null)
				{
					oos.close();
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
