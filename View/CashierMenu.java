package shop.management.system.view;

import java.text.DecimalFormat;
import java.util.List;

import shop.management.system.domain.Product;
import shop.management.system.model.ManagementOperation;
import shop.management.system.model.ProductOperation;
import shop.management.system.model.ReadUtility;

/**
 * class Cashier
 * 前台收银页面设置类
 * 
 * @author Hang Gao
 *
 */
public class CashierMenu
{

	public CashierMenu()
	{

	}

	/**
	 * UI of Cashier 前台收银页面
	 */
	public void mainMenu(ProductOperation productOperation, ManagementOperation managementOperation)
	{
		char number;

		System.out.println("Welcome to Supermarket Management System!");
		System.out.println("\n1.Login");
		System.out.println("\n2.Exit");
		System.out.println("*************************");
		System.out.println("\nPlease Choose");

		number = ReadUtility.readMenuTwoSelection();

		switch (number)
		{
		case '1':
			loginMenu(productOperation, managementOperation);
			break;
		case '2':
			MainMenu mainMenu = new MainMenu();
			mainMenu.mainMenu();
			break;
		}
	}

	/**
	 * UI for Cashier login 收银员登陆页面
	 */
	private void loginMenu(ProductOperation productOperation, ManagementOperation managementOperation)
	{
		String userName;
		String password;
		int mark = 3;
		boolean flag;

		for (;;)
		{

			System.out.print("Username：");
			userName = ReadUtility.readString(30);
			System.out.print("Password：");
			password = ReadUtility.readString(30);
			flag = managementOperation.logIn(userName, password);

			if (flag)
			{
				checkoutMenu(productOperation);
				break;
			} else
			{
				mark--;
				if (mark != 0)
				{
					System.out.println("\n\nUsername Password Do Not Match！");
					System.out.println("You Have " + mark + " Opportunaties. Please Try Again: ");
				} else
				{
					System.out.println("Login Failed! ");
					System.exit(0);
				}
			}
		}
	}

	/**
	 * UI for cashier checkout 收银员结账页面
	 */
	private void checkoutMenu(ProductOperation productOperation)
	{
		String name;
		int salesVolume;
		double totalPrice = 0; // Totale price of products saled
		double consumerPrice; // Consumers pay
		double backPrice; // Consumers exchange
		List<Product> list;
		Product product;
		boolean flag = true;
		boolean temp = true;
		int index = -1;
		char letter;

		System.out.println("\t\t\t1.Total Purchase");

		while (flag)
		{
			temp = true;
			while (temp)
			{
				System.out.println("Input Product Keywords: ");

				name = ReadUtility.readString(30);

				list = productOperation.readProduct();
				System.out.println("Product Name" + "\t\t\tProduct Pric" + "\t\t\tProduct Amount" + "\t\t\tNote");
				for (Product pro : list)
				{
					index = pro.getName().indexOf(name);
					if (index != -1)
					{
						temp = false;
						if (pro.getAmount() < 10)
						{
							System.out.println(pro.getName() + "\t\t\t" + pro.getPrice() + "\t\t\t" + pro.getAmount()
									+ "\t\t\t*Amount is Under 10!");
						} else
						{
							System.out.println(pro.getName() + "\t\t\t" + pro.getPrice() + "\t\t\t" + pro.getAmount());
						}
					}
				}

				if (temp == true)
				{
					System.out.println("Product with Keywords is Not Found!\n");
				}
			}

			for (;;)
			{
				System.out.println("Please choose Product: ");
				name = ReadUtility.readString(30);
				product = productOperation.checkProduct(name);

				if (product != null)
				{
					break;
				}
			}

			for (;;)
			{
				System.out.println("Please Enter Amount:　");
				salesVolume = ReadUtility.readInt();

				if (salesVolume <= product.getAmount())
				{
					totalPrice += product.getPrice() * salesVolume;
					productOperation.saleProducts(product, salesVolume);// Sale
																		// Product
																		// 售出商品方法
					break;
				} else
				{
					System.out.println("Amount not Enough! ");
				}
			}

			DecimalFormat df = new DecimalFormat("0.00");

			System.out.println(name + "\t\t\t$" + product.getPrice() + "\t\t\tPruchase Amount" + salesVolume
					+ "Total Price" + df.format(product.getPrice() * salesVolume));

			System.out.println("\n\nContinue? (y/n)");
			for (;;)
			{
				letter = ReadUtility.readChar();
				if (letter == 'y' || letter == 'Y')
				{
					break;
				} else if (letter == 'n' || letter == 'N')
				{
					System.out.println("Total: " + df.format(totalPrice));
					flag = false;
					break;
				} else
				{
					System.out.println("Wrong Input: Please Try Again!");
				}
			}
		}

		for (;;)
		{
			System.out.println("Please Input Recieved Money: ");
			consumerPrice = ReadUtility.readDouble();
			if (consumerPrice < totalPrice)
			{
				System.out.println("Not Enough! ");
			} else
			{
				backPrice = consumerPrice - totalPrice;
				System.out.println("Exchange: " + backPrice);
				System.out.println("Thank you!");
				break;
			}
		}

		MainMenu mainMenu = new MainMenu();
		mainMenu.mainMenu();
	}
}
