package com.dgit.department;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dgit.department.setting.ExportSettingService;
import com.dgit.department.setting.ImportSettingService;
import com.dgit.department.setting.InitSettingService;
import com.dgit.department.setting.SettingService;
import java.awt.GridLayout;

public class SettingUI extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JButton btnInit;
	private JButton btnExport;
	private JButton btnImport;
	private SettingService service;
	
	
	public static void main(String[] args) {
		new SettingUI();	
	}
	public SettingUI() {		
		setTitle("DB관리 메뉴");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 20, 0));
		
		btnInit = new JButton("초기화");
		btnInit.addActionListener(this);
		
		contentPane.add(btnInit);
		
		btnExport = new JButton("백업");
		btnExport.addActionListener(this);
		contentPane.add(btnExport);
		
		btnImport = new JButton("복원");
		btnImport.addActionListener(this);
		contentPane.add(btnImport);
		
		setVisible(true);		
		pack();
	}	
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnImport) {
			actionPerformedBtnImport(arg0);
		}
		if (arg0.getSource() == btnExport) {
			actionPerformedBtnExport(arg0);
		}
		if (arg0.getSource() == btnInit) {
			actionPerformedBtnInit(arg0);
		}
	}
	protected void actionPerformedBtnInit(ActionEvent arg0) {
		//초기화
		service = new InitSettingService();
		service.doSetting();
		
	}
	protected void actionPerformedBtnExport(ActionEvent arg0) {
		//백업
		service = new ExportSettingService();
		service.doSetting();
		
	}
	protected void actionPerformedBtnImport(ActionEvent arg0) {
		//복원
		service = new ImportSettingService();
		service.doSetting();		
	}
}
