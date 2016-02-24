package shop.management.system.view;

import java.util.List;

import shop.management.system.domain.Product;
import shop.management.system.exception.ProductNotFoundException;
import shop.management.system.model.ProductOperation;
import shop.management.system.model.ReadUtility;

/**
 * Product Maintain
 * 商品维护页面设置类
 * 
 * @author Hang Gao
 * 
 */
public class ProductMaintainMenu
{

	public ProductMaintainMenu()
	{

	}

	/**
	 * Class: Product Maintain 商品维护页面
	 */
	public void mainMenu(ProductOperation operation)
	{

		System.out.println("\tSupermarket Management System: Product Maintain\n");
		System.out.println("*****************************************\n");
		System.out.println("\t\t1.Add Product\n");
		System.out.println("\t\t2.Update Product\n");
		System.out.println("\t\t3.Delete Product\n");
		System.out.println("\t\t4.Display Product\n");
		System.out.println("\t\t5.Search Product\n");
		System.out.println("*****************************************\n");
		System.out.println("Please choose: Input 0 back to main menu: ");

		MainMenu m = new MainMenu();
		char letter = ReadUtility.readMenuFiveSelection();
		switch (letter)
		{
		case '0':
			System.out.println("Back to Main Menu\n\n");
			m.mainMenu();
			break;
		case '1':
			System.out.println("Execute Create Product\n\n");
			addProductMenu(operation);
			break;
		case '2':
			System.out.println("Execute update Product\n\n");
			updateProductMenu(operation);
			break;
		case '3':
			System.out.println("Execute delete Product\n\n");
			deleteProductMenu(operation);
			break;
		case '4':
			System.out.println("Execute Display Product\n\n");
			displayProductMenu(operation);
			break;
		case '5':
			System.out.println("Execute Search Product\n\n");
			searchProductMenu(operation);
			break;
		}
	}

	/**
	 * Create Porduct 
	 * 添加商品页面
	 */
	private void addProductMenu(ProductOperation operation)
	{
		char letter;
		String name;
		double price;
		int amount;

		System.out.println("Create Product Name: ");
		name = ReadUtility.readString(30);
		System.out.println("Create Product Price: ");
		price = ReadUtility.readDouble();
		System.out.println("Create Porduct Amout: ");
		amount = ReadUtility.readInt();
		operation.addProduct(name, price, amount);

		System.out.println("\n\nContinue (y/n)");
		for (;;)
		{
			letter = ReadUtility.readChar();
			if (letter == 'y' || letter == 'Y')
			{
				addProductMenu(operation);
				break;
			} else if (letter == 'n' || letter == 'N')
			{
				mainMenu(operation);
				break;
			} else
			{
				System.out.println("Wrong Input: Please Try Again!");
			}
		}
	}

	/**
	 * Product Update
	 * 更改商品页面
	 * 
	 * @param operation
	 *            操作执行对象
	 */
	private void updateProductMenu(ProductOperation operation)
	{
		char letter;
		char number;
		String name;
		double price;
		int amount;
		Product pro = null;

		for (;;)
		{
			System.out.println("Input Product Name");
			name = ReadUtility.readString(30);
			System.out.println("Product Name" + "\t\tProduct Price" + "\t\tProduct Amount");
			pro = operation.checkProduct(name);
			try
			{
				if (pro != null)
				{
					System.out.println(pro.getName() + "\t\t" + pro.getPrice() + "\t\t" + pro.getAmount());
					break;
				} else
				{
					throw new ProductNotFoundException("Product Do Not Exist!");
				}
			} catch (ProductNotFoundException e)
			{
				System.out.println(e.getMessage());
			}
		}

		System.out.println("\nChoose Update Informaton: ");
		System.out.println("1. Update Product Name");
		System.out.println("2. Update Product Price");
		System.out.println("3. Update Product Amount");
		number = ReadUtility.readMenuThreeSelection();
		switch (number)
		{
		case '1':
			System.out.println("Please Input Product Name: ");
			name = ReadUtility.readString(30);
			operation.updateProductName(pro, name);
			System.out.println("Update Success!");
			break;
		case '2':
			System.out.println("Please Input Product Price: ");
			price = ReadUtility.readDouble();
			operation.updateProductPrice(pro, price);
			System.out.println("Update Success!");
			break;
		case '3':
			System.out.println("Please Input Product Amount: ");
			amount = ReadUtility.readInt();
			operation.updateProductAmount(pro, amount);
			System.out.println("Update Success!");
			break;
		}

		System.out.println("\n\nContinue (y/n)");
		for (;;)
		{
			letter = ReadUtility.readChar();
			if (letter == 'y' || letter == 'Y')
			{
				updateProductMenu(operation);
				break;
			} else if (letter == 'n' || letter == 'N')
			{
				mainMenu(operation);
				break;
			} else
			{
				System.out.println("Wrong Input: Please Try Again!");
			}
		}
	}

	/**
	 * Delete Product
	 * 删除商品页面
	 * 
	 * @param operation
	 *            操作执行对象
	 */
	private void deleteProductMenu(ProductOperation operation)
	{
		char letter;
		String name;
		int index;

		System.out.println("Input Delete Product Name: ");
		name = ReadUtility.readString(30);
		index = operation.check(name);

		try
		{
			if (index != -1)
			{
				operation.deleteProduct(index);
				System.out.println("Delete Success!");
			} else
			{
				throw new ProductNotFoundException("Product Not Exist!");
			}
		} catch (ProductNotFoundException e)
		{
			System.out.println(e.getMessage());
		}

		System.out.println("\n\ncontinue (y/n)");
		for (;;)
		{
			letter = ReadUtility.readChar();
			if (letter == 'y' || letter == 'Y')
			{
				deleteProductMenu(operation);
				break;
			} else if (letter == 'n' || letter == 'N')
			{
				mainMenu(operation);
				break;
			} else
			{
				System.out.println("Wrong Input: Please Try Again!");
			}
		}
	}

	/**
	 * Display Product
	 * 显示所有商品页面
	 * 
	 * @param operation
	 *            操作执行对象
	 */
	private void displayProductMenu(ProductOperation operation)
	{
		int number;
		List<Product> list;

		System.out.println("Product Name" + "\t\t\tProduct Price" + "\t\t\tProduct Amount" + "\t\t\tNote");
		list = operation.displayAll();
		for (Product pro : list)
		{
			if (pro.getAmount() < 10)
			{
				System.out.println(
						pro.getName() + "\t\t\t" + pro.getPrice() + "\t\t\t" + pro.getAmount() + "\t\t\t*Amount is Under 10!");
			} else
			{
				System.out.println(pro.getName() + "\t\t\t" + pro.getPrice() + "\t\t\t" + pro.getAmount());
			}
		}
		System.out.println("\n\nInput 0 Back to Main Menuu:");
		number = ReadUtility.readInt();
		if (number == 0)
		{
			mainMenu(operation);
		} else
		{
			displayProductMenu(operation);
		}
	}

	/**
	 * Search Product
	 * 查询商品页面
	 * 
	 * @param operation
	 *            操作执行对象
	 */
	private void searchProductMenu(ProductOperation operation)
	{
		char number;
		char letter;
		List<Product> list;

		System.out.println("\n1. Search Product by Product Amount in ascending order");
		System.out.println("2. Search Product by Product Price in ascending order");
		System.out.println("3. Search Product by Keywords");
		System.out.println("Please Choose: Input 0 Back to Main Menu");

		number = ReadUtility.readMenuThreeSelection();
		list = operation.searchProduct(number);

		if (number == '3')
		{
			System.out.println("Input Product Keywords: ");
			String str = ReadUtility.readString(30);
			list = operation.readProduct();
			System.out.println("Product Name" + "\t\t\tProduct Price" + "\t\t\tProduct Amount" + "\t\t\tNote");
			for (Product pro : list)
			{
				if (pro.getName().indexOf(str) != -1)
				{
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
		} else
		{
			System.out.println("Product Name" + "\t\t\tProduct Price" + "\t\t\tProduct Amount" + "\t\t\tNote");
			list = operation.readProduct();
			for (Product pro : list)
			{
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

		if (number == '0')
		{
			mainMenu(operation);
		}

		System.out.println("\n\nContinue (y/n)");
		for (;;)
		{
			letter = ReadUtility.readChar();
			if (letter == 'y' || letter == 'Y')
			{
				searchProductMenu(operation);
				break;
			} else if (letter == 'n' || letter == 'N')
			{
				mainMenu(operation);
				break;
			} else
			{
				System.out.println("Wrong Input: Please Try Again!");
			}
		}
	}
}
