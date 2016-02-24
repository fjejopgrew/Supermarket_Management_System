package shop.management.system.view;

import java.util.List;

import shop.management.system.domain.Cashier;
import shop.management.system.domain.Product;
import shop.management.system.exception.CashierHasExit;
import shop.management.system.exception.CashierNotFound;
import shop.management.system.exception.ProductNotFoundException;
import shop.management.system.model.ManagementOperation;
import shop.management.system.model.ProductOperation;
import shop.management.system.model.ReadUtility;

/**
 * class: Product Management
 * 商品管理类页面
 * 
 * @author Hang Gao
 *
 */
public class ProductManagementMenu
{

	public ProductManagementMenu()
	{

	}

	public void mainMenu(ProductOperation productOperation, ManagementOperation managementOperation)
	{

		System.out.println("\tSupermarket Management System: Product Operation\n");
		System.out.println("*****************************************\n");
		System.out.println("\t\t1. Display Products Saled Today\n");
		System.out.println("\t\t2. Create Cashier Account\n");
		System.out.println("\t\t3. Update Cashier Account\n");
		System.out.println("\t\t4. Delete Cashier Account\n");
		System.out.println("\t\t5. Display Cashiers\n");
		System.out.println("*****************************************\n");
		System.out.println("Please Choose: Input 0 Back to Main Menu: ");

		char number;
		number = ReadUtility.readMenuFiveSelection();
		switch (number)
		{
		case '0':
			MainMenu mainMenu = new MainMenu();
			mainMenu.mainMenu();
			break;
		case '1':
			displayAllSalesMenu(productOperation, managementOperation);
			break;
		case '2':
			addCashierMenu(productOperation, managementOperation);
			break;
		case '3':
			updateCashierMenu(productOperation, managementOperation);
			break;
		case '4':
			deleteCashierMenu(productOperation, managementOperation);
			break;
		case '5':
			displayCashierMenu(productOperation, managementOperation);
			break;
		}
	}

	/**
	 * Display Products Saled Today 显示今日卖出的所有商品的页面
	 * 
	 * @param operation
	 *            商品管理对象
	 */
	private void displayAllSalesMenu(ProductOperation productOperation, ManagementOperation managementOperation)
	{
		char letter;
		List<Product> salesList;
		System.out.println("\n\nProducts Saled Today: ");
		System.out
				.println("Product Name" + "\t\tProduct Price" + "\t\tProduct Amount" + "\t\tSales Amount" + "\t\tNote");
		salesList = productOperation.displayAllSales();
		for (Product pro : salesList)
		{
			if (pro.getAmount() < 10)
			{
				System.out.println(pro.getName() + "\t\t" + pro.getPrice() + "\t\t" + pro.getAmount() + "\t\t"
						+ pro.getSalesVolume() + "\t\t*Amount is Under 10!");
			} else
			{
				System.out.println(pro.getName() + "\t\t" + pro.getPrice() + "\t\t" + pro.getAmount() + "\t\t"
						+ pro.getSalesVolume());
			}
		}

		System.out.println("\n\nInput 0 to Back to Main Menu!");
		for (;;)
		{
			letter = ReadUtility.readChar();
			if (letter == '0')
			{
				mainMenu(productOperation, managementOperation);
				break;
			} else
			{
				System.out.println("Wrong Input: Please Try Again!");
			}
		}
	}

	/**
	 * Create Cashier Account 添加收银员账号页面
	 * 
	 * @param operation
	 *            商品管理对象
	 */
	private void addCashierMenu(ProductOperation productOperation, ManagementOperation managementOperation)
	{
		char letter;
		String userName;
		String password;
		String againPassword;

		for (;;)
		{
			try
			{
				System.out.println("Please Input Cashier Username: ");
				userName = ReadUtility.readString(30);
				if (managementOperation.check(userName) == null)
				{
					System.out.println("Please Input Password: ");
					password = ReadUtility.readString(30);
					System.out.println("Please Input Password Again: ");
					againPassword = ReadUtility.readString(30);
					managementOperation.addCashier(userName, password, againPassword);
					System.out.println("\nCreate Success！");
					break;
				} else
				{
					throw new CashierHasExit("Username Already Exist!");
				}
			} catch (CashierHasExit e)
			{
				System.out.println(e.getMessage());
			}
		}

		System.out.println("\n\nContinue (y/n)");
		for (;;)
		{
			letter = ReadUtility.readChar();
			if (letter == 'y' || letter == 'Y')
			{
				addCashierMenu(productOperation, managementOperation);
				break;
			} else if (letter == 'n' || letter == 'N')
			{
				mainMenu(productOperation, managementOperation);
				break;
			} else
			{
				System.out.println("Wrong Input: Please Try Again!");
			}
		}
	}

	/**
	 * Update Cashier Account
	 * 
	 * 更改收银员账号页面
	 * 
	 * @param operation
	 *            商品管理对象
	 */

	private void updateCashierMenu(ProductOperation productOperation, ManagementOperation managementOperation)
	{
		char letter;
		int number;
		String userName;
		String originPassword;
		String password;
		String againPassword;
		Cashier cashier;

		Product pro = null;

		System.out.println("Input Update Cashier Username: ");
		userName = ReadUtility.readString(30);
		cashier = managementOperation.check(userName);

		try
		{
			if (cashier != null)
			{
				System.out.println("\nChoose What to Update: ");
				System.out.println("1. Update Cashier Username: ");
				System.out.println("2. Update Cashier Password: ");
				number = ReadUtility.readInt();

				switch (number)
				{
				case 1:
					System.out.println("Please Input New Username");
					userName = ReadUtility.readString(30);
					managementOperation.updateUserName(cashier, userName);
					System.out.println("Update success!");
					break;
				case 2:
					System.out.println("Please Input Old Password: ");
					originPassword = ReadUtility.readString(30);
					System.out.println("Please Input New Password: ");
					password = ReadUtility.readString(30);
					System.out.println("Please Input New Password Again: ");
					againPassword = ReadUtility.readString(30);
					if (originPassword.equals(cashier.getPassword()))
					{
						if (password.equals(againPassword))
						{
							managementOperation.updatePassword(cashier, password);
							System.out.println("Update success!");
						} else
						{
							System.out.println("Passwords do Not Match");
						}
					} else
					{
						System.out.println("Wrong Password!");
					}
					break;
				}
			} else
			{
				throw new CashierNotFound("Cashier Do Not Exist!");
			}
		} catch (CashierNotFound e)
		{
			System.out.println(e.getMessage());
		}

		System.out.println("\n\nContinue (y/n)");
		for (;;)
		{
			letter = ReadUtility.readChar();
			if (letter == 'y' || letter == 'Y')
			{
				updateCashierMenu(productOperation, managementOperation);
				break;
			} else if (letter == 'n' || letter == 'N')
			{
				mainMenu(productOperation, managementOperation);
				break;
			} else
			{
				System.out.println("Wrong Input: Please Try Again!");
			}
		}
	}

	/**
	 * Delete Cashier Account
	 * 
	 * 删除收银员账号页面
	 * 
	 * @param operation
	 *            商品管理对象
	 */
	private void deleteCashierMenu(ProductOperation productOperation, ManagementOperation managementOperation)
	{
		char letter;
		String userName;
		int index;

		System.out.println("Input Delete Cashier Username: ");
		userName = ReadUtility.readString(30);
		index = managementOperation.checkIndex(userName);

		try
		{
			if (index != -1)
			{
				managementOperation.deleteCashier(index);
				System.out.println("Delete Success!");
			} else
			{
				throw new CashierNotFound("Username Do Not Exist!");
			}
		} catch (CashierNotFound e)
		{
			System.out.println(e.getMessage());
		}

		System.out.println("\n\nContinue (y/n)");
		for (;;)
		{
			letter = ReadUtility.readChar();
			if (letter == 'y' || letter == 'Y')
			{
				deleteCashierMenu(productOperation, managementOperation);
				break;
			} else if (letter == 'n' || letter == 'N')
			{
				mainMenu(productOperation, managementOperation);
				break;
			} else
			{
				System.out.println("Wrong Input: Please Try Again!");
			}
		}
	}

	/**
	 * Display Cashiers
	 * 显示所有注册的账户名
	 * 
	 * @param operation
	 *            账户操作对象
	 */
	private void displayCashierMenu(ProductOperation productOperation, ManagementOperation managementOperation)
	{
		char letter;
		List<Cashier> list = null;
		list = managementOperation.displayAllCashier();
		System.out.println("\n\nAll Cashier Username: ");

		for (Cashier ca : list)
		{
			System.out.println(ca.getUserName());
		}

		System.out.println("\n\nInput 0 Back to Last Menu");
		for (;;)
		{
			letter = ReadUtility.readChar();
			if (letter == '0')
			{
				mainMenu(productOperation, managementOperation);
				break;
			} else
			{
				System.out.println("Wrong Input: Please Try Again!");
			}
		}
	}
}
