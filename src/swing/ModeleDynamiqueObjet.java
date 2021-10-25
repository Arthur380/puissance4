package swing;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class ModeleDynamiqueObjet extends AbstractTableModel {
	private final ImageIcon iconJ = new ImageIcon(new ImageIcon("C:\\Users\\didiw\\Downloads\\Jaune.png").getImage()
			.getScaledInstance(20, 20, Image.SCALE_DEFAULT));
	private final ImageIcon iconV = new ImageIcon(new ImageIcon("C:\\Users\\didiw\\Downloads\\Rouge.png").getImage()			.getScaledInstance(20, 20, Image.SCALE_DEFAULT));
	//private final ImageIcon iconV = new ImageIcon(new ImageIcon("").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
	private final String[] entetes = { "0", "1", "2", "3", "4", "5", "6" };
	   private final Object[][] donnees;

	public ModeleDynamiqueObjet() {
		super();
		donnees = new Object[][]{{ iconV, iconV, iconV, iconV, iconV, iconV, iconV },
				{ iconV, iconV, iconV, iconV, iconV, iconV, iconV },
				{ iconV, iconV, iconV, iconV, iconV, iconV, iconV },
				{ iconV, iconV, iconV, iconV, iconV, iconV, iconV },
				{ iconV, iconV, iconV, iconV, iconV, iconV, iconV },
				{ iconV, iconV, iconV, iconV, iconV, iconV, iconV },
				{ iconV, iconV, iconV, iconV, iconV, iconV, iconV }, };
	}

	public int getColumnCount() {
		return entetes.length;
	}

	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	@Override
	public Class getColumnClass(int colonne) {
		return getValueAt(0, colonne).getClass();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void SetValueRowCol(int row, int column, char joueur) {
		if (joueur == 'x') {
			donnees[row][column] = iconV;
		} else
			donnees[row][column] = iconJ;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}