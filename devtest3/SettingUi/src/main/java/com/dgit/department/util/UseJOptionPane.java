package com.dgit.department.util;

import javax.swing.JOptionPane;

public class UseJOptionPane {
	public static void showMessage(String message){
		JOptionPane.showMessageDialog(null, message, "메시지", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void showWarningMessage(String message){
		JOptionPane.showMessageDialog(null, message, "메시지", JOptionPane.WARNING_MESSAGE);
	}
}
