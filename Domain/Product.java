package shop.management.system.domain;

import java.io.Serializable;

/**
 * Product Class 商品类
 * 
 * @author Hang Gao
 * 
 */
public class Product implements Comparable<Product>, Serializable
{
	private String name; // Product name 商品的名称
	private double price; // Product price 商品的价格
	private int amount; // Product amount 商品的数量
	private int salesVolume;// sales volume of product 商品的销量

	public Product()
	{

	}

	public Product(String name, double price, int amount)
	{
		super();
		this.name = name;
		this.price = price;
		this.amount = amount;

	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public int getAmount()
	{
		return amount;
	}

	public void setAmount(int amount)
	{
		this.amount = amount;
	}

	public int getSalesVolume()
	{
		return salesVolume;
	}

	public void setSalesVolume(int salesVolume)
	{
		this.salesVolume = salesVolume;
	}

	/**
	 * Implement and override compareTo method Sort the product by amount in
	 * ascending order 实现接口中的compareTo方法进行商品数量的升序排序
	 */
	@Override
	public int compareTo(Product pro)
	{
		return this.amount - pro.getAmount();
	}
}
