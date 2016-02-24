package shop.management.system.view;

import java.util.Scanner;

import shop.management.system.model.ManagementOperation;
import shop.management.system.model.ProductOperation;
import shop.management.system.model.ReadUtility;

/**
 * Class: Main Menu
 * 
 * @author Hang Gao
 * 
 */
public class MainMenu
{
	private ProductOperation productOperation = null;
	private ManagementOperation managementOperation = null;

	public MainMenu()
	{
		productOperation = new ProductOperation();
		managementOperation = new ManagementOperation();
	}

	/**Main Menu
	 * 商超管理系统主页面
	 */
	public void mainMenu()
	{

		System.out.println("\n\t\tSupermarket Management System");
		System.out.println("*****************************************\n");
		System.out.println("\t\t1.Product Operation\n");
		System.out.println("\t\t2.Cashier\n");
		System.out.println("\t\t3.Product Management\n");
		System.out.println("*****************************************\n");
		System.out.println("Please Choose: Input 0 to Exit");

		char number = ReadUtility.readMenuThreeSelection();
		switch (number)
		{
		case '0':
			System.out.println("Thank You for Using");
			System.exit(0);
			break;
		case '1':
			System.out.println("\nExecute Product Maintain\n\n");
			ProductMaintainMenu proM = new ProductMaintainMenu();
			proM.mainMenu(productOperation);
			break;
		case '2':
			System.out.println("\nExecute Cashier Management\n\n");
			CashierMenu caM = new CashierMenu();
			caM.mainMenu(productOperation, managementOperation);
			break;
		case '3':
			System.out.println("\nExecute Porduct Management\n\n");
			ProductManagementMenu proMM = new ProductManagementMenu();
			proMM.mainMenu(productOperation, managementOperation);
			break;
		}

	}

}
