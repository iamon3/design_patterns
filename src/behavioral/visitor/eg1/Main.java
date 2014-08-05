package behavioral.visitor.eg1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This implementation is without using reflection.
 */
public class Main {
	public static void main(String[] args) {

		Customer c = new Customer("customer1");
		c.addOrder(new Order(".order1", "..item1"));
		c.addOrder(new Order(".order2", "..item1"));
		c.addOrder(new Order(".order3", "..item1"));

		Customer c2 = new Customer("customer2");
		Order o = new Order(".order_a");
		o.addItem(new Item("..item_a1"));
		o.addItem(new Item("..item_a2"));
		o.addItem(new Item("..item_a3"));
		c2.addOrder(o);
		c2.addOrder(new Order(".order_b", "..item_b1"));


		CustomerGroup customers = new CustomerGroup();
		customers.addCustomer(c);
		customers.addCustomer(c2);

		GeneralReport visitor = new GeneralReport(); 

		customers.accept(visitor);

		visitor.displayResults();
	}
}

interface IVisitable {
	public void accept(IVisitor visitor);
}

interface IVisitor {
	public void visit(Customer customer);
	public void visit(Order order);
	public void visit(Item item); 
}

class GeneralReport implements IVisitor{

	private int customersNo;
	private int ordersNo;
	private int itemsNo;

	public void visit(Customer customer)
	{
		System.out.println(customer.getName());
		customersNo ++;
	}
	public void visit(Order order)
	{
		System.out.println(order.getName());
		ordersNo++;
	}
	public void visit(Item item)
	{
		System.out.println(item.getName());
		itemsNo++;
	}

	public void displayResults()
	{
		System.out.println("Nr of customers:" + customersNo);
		System.out.println("Nr of orders:   " + ordersNo);
		System.out.println("Nr of itemss:   " + itemsNo);
	}
}

class CustomerGroup {

	private ArrayList customers = new ArrayList();

	public void accept(IVisitor visitor)
	{
		for (Iterator it=customers.iterator(); it.hasNext();)
		{
			((Customer)it.next()).accept(visitor);
		}		
	}

	public void addCustomer(Customer customer)
	{
		customers.add(customer);
	}	

}

class Customer implements IVisitable{

	private String name;

	private ArrayList orders = new ArrayList();

	public void accept(IVisitor visitor)
	{
		visitor.visit(this);

		for (Iterator it=orders.iterator(); it.hasNext();)
		{
			((IVisitable)it.next()).accept(visitor);
		}		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public void addOrder(Order order)
	{
		orders.add(order);
	}


	public Customer(String name)
	{
		this.name = name;
	}
}

class Order implements IVisitable {

	private String name;

	private ArrayList items = new ArrayList();

	public Order(String name)
	{
		this.name = name;
	}

	public Order(String name, String itemName)
	{
		this.name = name;
		this.addItem(new Item(itemName));
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void accept(IVisitor visitor)
	{
		visitor.visit(this);

		for (Iterator it=items.iterator(); it.hasNext();)
		{
			((Item)it.next()).accept(visitor);
		}
	}


	public void addItem(Item item)
	{
		items.add(item);
	}
}

class Item implements IVisitable{

	private String name;

	public void accept(IVisitor visitor)
	{
		visitor.visit(this);
	}


	public Item(String name)
	{
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

/**
 * abstract class Visitor {

	public abstract void visit(Customer customer);
	public abstract void visit(Order order);
	public abstract void visit(Item item);
	public abstract void defaultVisit(Object object);
	
	public void visit(Object object) {
		try
		{
			Method downPolymorphic = object.getClass().getMethod("visit",
				new Class[] { object.getClass() });

			if (downPolymorphic == null) {
				defaultVisit(object);
			} else {
				downPolymorphic.invoke(this, new Object[] {object});
			}
		}
		catch (NoSuchMethodException e)
		{
			this.defaultVisit(object);
		}
		catch (InvocationTargetException e)
		{
			this.defaultVisit(object);
		}   
		catch (IllegalAccessException e)
		{
			this.defaultVisit(object);
		}      	
	}
}

void defaultVisit(Object object)
{
	// if we don't know the class we do nothing
	if (object.getClass().equals(Product.class))
	{
		System.out.println("default visit: "
			+ object.getClass().getSimpleName());
		itemsNo++;
	}
}

*/ 