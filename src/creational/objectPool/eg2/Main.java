package creational.objectPool.eg2;

import java.sql.Connection;

import creational.objectPool.eg2.factories.JDBCConnectionFactory;

public class Main
{
	public static void main(String[] args)
	{
		Pool < Connection > pool = 
				new BoundedBlockingPool < Connection > (
						10, 
						new JDBCConnectionValidator(),
						new JDBCConnectionFactory("", "", "", "")
						);

		//do whatever you like
	}
}