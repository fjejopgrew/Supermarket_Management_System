package shop.management.system.model;

import java.util.*;

/**
 * 用户键盘输入工具类
 * 
 * @author Johnny
 *
 */
public class ReadUtility
{
	private static Scanner scanner = new Scanner(System.in);

	/**
	 * Read char that user typed 读取用户输入单个字符
	 * 
	 * @return : chars must be within '0','1','2','3','4','5'
	 *         返回用户输入的单个数字字符,该字符只能是'0','1','2','3','4','5'
	 */
	public static char readMenuFiveSelection()
	{
		char c;
		for (;;)
		{
			String str = readKeyBoard(1, false);
			c = str.charAt(0);
			if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5')
			{
				System.out.print("Wrong Input! Please Try Again: ");
			} else
				break;
		}
		return c;
	}

	/**
	 * Read char that user typed 读取用户输入单个字符
	 * 
	 * @return :chars must be within '0','1','2','3','4'
	 *         返回用户输入的单个数字字符,该字符只能是'0','1','2','3','4'
	 */
	public static char readMenuFourSelection()
	{
		char c;
		for (;;)
		{
			String str = readKeyBoard(1, false);
			c = str.charAt(0);
			if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4')
			{
				System.out.print("Wrong Input! Please Try Again: ");
			} else
				break;
		}
		return c;
	}

	/**
	 * Read char that user typed 读取用户输入单个字符
	 * 
	 * @return :chars must be within '0','1','2','3',
	 *         返回用户输入的单个数字字符,该字符只能是'0','1','2','3'
	 */
	public static char readMenuThreeSelection()
	{
		char c;
		for (;;)
		{
			String str = readKeyBoard(1, false);
			c = str.charAt(0);
			if (c != '0' && c != '1' && c != '2' && c != '3')
			{
				System.out.print("Wrong Input! Please Try Again: ");
			} else
				break;
		}
		return c;
	}

	/**
	 * Read char that user typed 读取用户输入单个字符
	 * 
	 * @return :chars must be within '0','1','2' 返回用户输入的单个数字字符,该字符只能是'0','1','2'
	 */
	public static char readMenuTwoSelection()
	{
		char c;
		for (;;)
		{
			String str = readKeyBoard(1, false);
			c = str.charAt(0);
			if (c != '0' && c != '1' && c != '2')
			{
				System.out.print("Wrong Input! Please Try Again: ");
			} else
				break;
		}
		return c;
	}

	/**
	 * Read characters that user typed 读取用户输入单个任意字符
	 * 
	 * @return : character 返回用户输入的单个任意字符
	 */
	public static char readChar()
	{
		String str = readKeyBoard(1, false);
		return str.charAt(0);
	}

	/**
	 * Read characters that user typed 读取用户输入单个任意字符
	 * 
	 * @param defaultValue
	 *            默认字符
	 * @return : if user typed return the character, else return default value
	 *         返回用户输入的字符, 如果用户不输入任何字符直接回车,返回该参数作为默认值
	 */
	public static char readChar(char defaultValue)
	{
		String str = readKeyBoard(1, true);
		return (str.length() == 0) ? defaultValue : str.charAt(0);
	}

	/**
	 * readintegers that user typed , 5 digits limited 读取用户输入的数字,用户只能输入5位数
	 * 
	 * @return : Integers that user typed in the keyboard 返回用户输入的数字
	 */
	public static int readInt()
	{
		int n;
		for (;;)
		{
			String str = readKeyBoard(5, false);
			try
			{
				n = Integer.parseInt(str);
				break;
			} catch (NumberFormatException e)
			{
				System.out.print("Wrong Number! Please Try Again: ");
			}
		}
		return n;
	}

	/**
	 * readintegers that user typed , 2 digits limited 读取用户输入的数字,用户只能输入2位数
	 * 
	 * @return : Integers that user typed in the keyboard 返回用户输入的数字
	 * @param defaultValue
	 *            默认值
	 * @return : if no integers found, return default value
	 */
	public static int readInt(int defaultValue)
	{
		int n;
		for (;;)
		{
			String str = readKeyBoard(2, true);
			if (str.equals(""))
			{
				return defaultValue;
			}

			try
			{
				n = Integer.parseInt(str);
				break;
			} catch (NumberFormatException e)
			{
				System.out.print("Wrong Number! Please Try Again: ");
			}
		}
		return n;
	}

	/**
	 * Read double that user typed 读取用户输入的浮点数
	 * 
	 * @return : typed double, if not exists, return double
	 *         返回用户输入的浮点数，如果用户不输入任何字符直接回车,返回该参数作为默认值
	 */
	public static double readDouble()
	{
		double n;
		for (;;)
		{
			String str = scanner.nextLine();
			try
			{
				n = Double.parseDouble(str);
				break;
			} catch (NumberFormatException e)
			{
				System.out.print("Wrong Number! Please Try Again: ");
			}
		}
		return n;
	}

	/**
	 * Read String that user typed 读取用户输入的字符串
	 * 
	 * @param limit:
	 *            length limit 用户输入的字符串长度限制
	 * @return : String that user typed 返回用户输入的字符串
	 */
	public static String readString(int limit)
	{
		return readKeyBoard(limit, false);
	}

	/**
	 * 读取用户输入的字符串
	 * 
	 * @param limit
	 *            用户输入的字符串长度限制
	 * @param defaultValue
	 *            默认值
	 * @return 返回用户输入的字符串,如果用户不输入任何字符直接回车,返回该参数作为默认值
	 */
	public static String readString(int limit, String defaultValue)
	{
		String str = readKeyBoard(limit, true);
		return str.equals("") ? defaultValue : str;
	}

	public static char readConfirmSelection()
	{
		char c;
		for (;;)
		{
			String str = readKeyBoard(1, false).toUpperCase();
			c = str.charAt(0);
			if (c == 'Y' || c == 'N')
			{
				break;
			} else
			{
				System.out.print("选择错误，请重新输入：");
			}
		}
		return c;
	}

	/**
	 * Read keyboard 似有方法在被其它方法调用
	 * 
	 * @param limit
	 * @param blankReturn
	 * @return
	 */
	private static String readKeyBoard(int limit, boolean blankReturn)
	{
		String line = "";

		while (scanner.hasNextLine())
		{
			line = scanner.nextLine();
			if (line.length() == 0)
			{
				if (blankReturn)
					return line;
				else
					continue;
			}

			if (line.length() < 1 || line.length() > limit)
			{
				System.out.print("length (<=" + limit + ") mistake，please try again: ");
				continue;
			}
			break;
		}

		return line;
	}
}
