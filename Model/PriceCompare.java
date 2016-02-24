package shop.management.system.model;

import java.util.Comparator;

import shop.management.system.domain.Product;

public class PriceCompare implements Comparator<Product>
{

	/**
	 * Implement Comparator Interface, sort products by price in ascending order
	 * 实现Comparaor接口中的方法,进行商品价格的升序排序
	 */
	public int compare(Product pro1, Product pro2)
	{
		double minus;
		minus = pro1.getPrice() - pro2.getPrice();
		if (minus > 0)
		{
			return 1;
		} else if (minus == 0)
		{
			return 0;
		} else
		{
			return -1;
		}
	}

}
