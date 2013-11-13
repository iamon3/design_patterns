package creational.builderDirector.eg3.withPattern;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 * The main() creates a reader/parser, and configures it with a builder 
 * (an object that implements a standard interface and knows how to create one of many possible "results". 
 * The reader reads and parses the common input and delegates the construction to the configured builder.
 * 
 * This implementation demonstrates the spirit of the Builder pattern, 
 * but it is more intricate, and probably cannot be justified for this fairly limited context. 
 */

public class BuilderDemo
{
	public static void main(String[] args)
	{
		Builder target = null;
		try
		{
			target = (Builder)Class.forName(args[0]).newInstance();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		Reader parser = new Reader(target);
		parser.construct("BuilderDemo.dat");

		JFrame frame = new JFrame("BuilderDemo - " + args[0]);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(target.get_result());
		frame.pack();
		frame.setVisible(true);
	}
}

class Reader
{
	private Builder m_builder;

	public Reader(Builder b)
	{
		m_builder = b;
	}

	public void construct(String file_name)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(file_name));
			String line, cell = "";
			String[] tokens;
			boolean first_line = true;
			while ((line = br.readLine()) != null)
			{
				tokens = line.split("\\s");
				int i = 0;
				if (first_line)
				{
					m_builder.set_width_and_height(Integer.parseInt(tokens[0]),
							Integer.parseInt(tokens[1]));
					i = 2;
					first_line = false;
				}
				for (; i < tokens.length; ++i)
					if (tokens[i].equals(""))
					{
						m_builder.build_cell(cell);
						cell = "";
						m_builder.start_row();
					}
					else if (tokens[i].equals(""))
					{
						m_builder.build_cell(cell);
						cell = "";
					}
					else
					{
						cell += " " + tokens[i];
					}
			}
			m_builder.build_cell(cell);
			br.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}


interface Builder
{
	void set_width_and_height(int width, int height);
	void start_row();
	void build_cell(String value);
	Component get_result();
}


class JTable_Builder implements Builder
{
	private JTable m_table;
	private TableModel m_model;
	private int i = 0, j = 0;

	public void set_width_and_height(int width, int height)
	{
		m_table = new JTable(height, width);
		m_model = m_table.getModel();
	}
	public void start_row()
	{
		++i;
		j = 0;
	}
	public void build_cell(String value)
	{
		m_model.setValueAt(value, i, j++);
	}
	public Component get_result()
	{
		return m_table;
	}
}

class GridLayout_Builder implements Builder
{
	private JPanel m_panel = new JPanel();

	public void set_width_and_height(int width, int height)
	{
		m_panel.setLayout(new GridLayout(height, width));
		m_panel.setBackground(Color.white);
	}
	public void start_row(){}
	public void build_cell(String value)
	{
		m_panel.add(new Label(value));
	}
	public Component get_result()
	{
		return m_panel;
	}
}

class GridBagLayout_Builder implements Builder
{
	private JPanel m_panel = new JPanel();
	private GridBagConstraints c = new GridBagConstraints();
	private int i = 0, j = 0;

	public void set_width_and_height(int width, int height)
	{
		m_panel.setLayout(new GridBagLayout());
		m_panel.setBackground(Color.white);
	}
	public void start_row()
	{
		++i;
		j = 0;
	}
	public void build_cell(String value)
	{
		c.gridx = j++;
		c.gridy = i;
		m_panel.add(new Label(value), c);
	}
	public Component get_result()
	{
		return m_panel;
	}
}