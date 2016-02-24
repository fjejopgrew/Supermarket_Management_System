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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

import shop.management.system.domain.Product;
import shop.management.system.exception.ProductHasExit;

/**
 * Method Class: Product Operation 方法类
 * 
 * @author Hang Gao
 * 
 */
public class ProductOperation
{
	private List<Product> list;// List of product 商品的集合
	private List<Product> salesList; // List of product that has saled 已卖商品的集合
	private String today;
	private SimpleDateFormat format = null;

	File fileProduct;
	File fileSales;

	public ProductOperation()
	{

	}

	public List<Product> getList()
	{
		list = readProduct();
		return list;
	}

	/**
	 * Add Product in the list 创建商品对象
	 * 
	 * @param name
	 *            商品名称
	 * @param price
	 *            商品价格
	 * @param amount
	 *            商品数量
	 */
	public void addProduct(String name, double price, int amount)
	{
		Product product = null;

		try
		{
			if (!isFind(name))
			{
				product = new Product(name, price, amount);
				list.add(product);
				writeProduct(list);
				System.out.println("Add successful!");
			} else
			{
				throw new ProductHasExit("This product is already added");
			}
		} catch (ProductHasExit e)
		{
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Search Product 查询商品名称
	 * 
	 * @param name
	 *            商品名称
	 * @return Product exists: TRUE ，Not exists: FALSE
	 */
	private boolean isFind(String name)
	{
		list = readProduct();
		for (Product pro : list)
		{
			if (name.equals(pro.getName()))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Change Prodcut Name 更改商品的名称
	 * 
	 * @param product
	 *            商品对象
	 * @param name
	 *            商品名称
	 */
	public void updateProductName(Product product, String name)
	{
		product.setName(name);
		writeProduct(list);
	}

	/**
	 * Change product price 更改商品的价格
	 * 
	 * @param product
	 *            商品对象
	 * @param price
	 *            商品价格
	 */
	public void updateProductPrice(Product product, double price)
	{
		product.setPrice(price);
		writeProduct(list);
	}

	/**
	 * Update product amount 更改商品的数量
	 * 
	 * @param product
	 *            商品对象
	 * @param amount
	 *            更改的数量
	 */
	public void updateProductAmount(Product product, int amount)
	{
		product.setAmount(amount);
		writeProduct(list);

		salesList = readSales();
		for (Product pro : salesList)
		{
			if (pro.getName().equals(product.getName()))
			{
				pro.setAmount(amount);
				writeSales(salesList);
				break;
			}
		}
	}

	/**
	 * Delete Product 删除商品
	 * 
	 * @param index:
	 *            product index
	 */
	public void deleteProduct(int index)
	{
		list = readProduct();
		list.remove(index);
		writeProduct(list);
	}

	/**
	 * Display all product 显示所有商品
	 */
	public List<Product> displayAll()
	{
		list = readProduct();
		return list;
	}

	/**
	 * Search Product by product ID 查询商品 Choose number to determine sort by
	 * amount or price in ascending order
	 * 
	 * @param number
	 *            选择序号进行对商品具体方式的查询
	 */
	public List<Product> searchProduct(char number)
	{
		list = readProduct();

		switch (number)
		{
		case '1':
			Collections.sort(list);// 采用商品数量升序查询
			writeProduct(list);
			break;
		case '2':
			Collections.sort(list, new PriceCompare());// 采用商品价格升序查询
			writeProduct(list);
			break;
		case '3':
			break;
		}
		list = readProduct();
		return list;
	}

	/**
	 * Products that is saled 售出商品
	 * 
	 * @param product
	 *            售出商品对象
	 * @param salesVolume
	 *            售出商品数量
	 */
	public void saleProducts(Product product, int salesVolume)
	{
		int index = -1;
		boolean flag = false;

		product.setAmount(product.getAmount() - salesVolume);
		product.setSalesVolume(product.getSalesVolume() + salesVolume);
		writeProduct(list);

		salesList = readSales();
		for (Product pro : salesList)
		{
			if (pro.getName().equals(product.getName()))
			{
				pro.setAmount(pro.getAmount() - salesVolume);
				pro.setSalesVolume(pro.getSalesVolume() + salesVolume);
				writeSales(salesList);
				flag = true;
				break;
			}
		}

		if (!flag)
		{
			salesList.add(product);
		}

		writeSales(salesList);
	}

	/**
	 * Search Product name 查询商品名称
	 * 
	 * @param name
	 *            商品名称
	 * @return : index of the product of product list
	 */
	public int check(String name)
	{
		int index = -1;
		list = readProduct();
		for (Product pro : list)
		{
			if (name.equals(pro.getName()))
			{
				index = list.indexOf(pro);
			}
		}
		return index;
	}

	/**
	 * Search Product name 查询商品名称
	 * 
	 * @param name
	 *            商品名称
	 * @return : Product Object 该商品名称对应的商品对象
	 */
	public Product checkProduct(String name)
	{
		Product pro1 = null;
		list = readProduct();
		for (Product pro2 : list)
		{
			if (name.equals(pro2.getName()))
			{
				pro1 = pro2;
			}
		}
		return pro1;
	}

	/**
	 * Display all sales product 显示所有卖出的商品
	 * 
	 * @return : list of all products that is saled 卖出商品的salesList集合
	 */
	public List<Product> displayAllSales()
	{
		salesList = readSales();
		return salesList;
	}

	/**
	 * Read list of product from file product.dat 读入文件中的list对象
	 * 
	 * @return list对象
	 */
	public List<Product> readProduct()
	{
		InputStream is = null;
		ObjectInputStream ois = null;

		try
		{
			if (fileProduct == null)
			{
				fileProduct = new File("product.dat");
				fileProduct.createNewFile();
			}

			is = new FileInputStream(fileProduct);
			ois = new ObjectInputStream(is);
			Object obj = ois.readObject();
			if (obj instanceof List)
			{
				list = (List<Product>) obj;
			}
		} catch (EOFException e)
		{
			list = new ArrayList<Product>();
			return list;
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
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
	 * Write product list to file product.dat 向文件写入list对象
	 * 
	 * @param list
	 * 
	 */
	public void writeProduct(List<Product> list)
	{
		OutputStream os = null;
		ObjectOutputStream oos = null;

		try
		{
			if (fileProduct == null)
			{
				fileProduct = new File("product.dat");
				fileProduct.createNewFile();
			}
			os = new FileOutputStream(fileProduct);
			oos = new ObjectOutputStream(os);

			oos.writeObject(list);
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
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

	/**
	 * Read product list that is saled today 读入今日已售出文件中的list对象
	 * 
	 * @return salesList对象
	 */
	public List<Product> readSales()
	{
		InputStream is = null;
		ObjectInputStream ois = null;

		try
		{
			format = new SimpleDateFormat("yyyy--MM--dd");
			today = format.format(new Date());
			fileSales = new File(today + ".dat");

			if (!fileSales.exists())
			{
				fileSales.createNewFile();
				list = readProduct();
				for (Product pro : list)
				{
					pro.setSalesVolume(0);
				}
				writeProduct(list);
			}

			is = new FileInputStream(fileSales); // 日期名称的文件
			ois = new ObjectInputStream(is);

			salesList = (List<Product>) ois.readObject();

		} catch (EOFException e)
		{
			salesList = new ArrayList<Product>();
			return salesList;
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
				if (ois != null)
				{
					ois.close();
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return salesList;
	}

	/**
	 * Write product list that is saled tody into file 向今日已售出文件写入list对象
	 * 
	 * @param salesList
	 *            salesList对象
	 */
	public void writeSales(List<Product> salesList)
	{
		OutputStream os = null;
		ObjectOutputStream oos = null;

		try
		{
			format = new SimpleDateFormat("yyyy--MM--dd");
			today = format.format(new Date());
			fileSales = new File(today + ".dat");
			if (!fileSales.exists())
			{
				fileSales.createNewFile();
			}
			os = new FileOutputStream(fileSales);
			oos = new ObjectOutputStream(os);

			oos.writeObject(salesList);
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
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
