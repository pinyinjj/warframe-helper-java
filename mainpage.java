
import java.awt.event.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.*;
import java.awt.*;


public class mainpage implements ActionListener ,ItemListener {
	private JFrame frmWarframeHelper;
	private JTextField textField;
	private JToggleButton startToggleButton = new JToggleButton("启动");
	private Choice choice = new Choice();
	public static JLabel status = new JLabel("等待");
	public static JRadioButton mag = new JRadioButton("Mag吸取");
	private static JRadioButton commander = new JRadioButton("切指挥官");
	static auto auto = new auto(false, 1, false);
	static Thread t = auto;
	private final JLabel SystemTime = new JLabel("");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					t.start();
					mainpage window = new mainpage();
					window.frmWarframeHelper.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
	}

	

	/**
	 * Create the application.
	 * @throws AWTException 
	 */
	public mainpage(){
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWarframeHelper = new JFrame();
		frmWarframeHelper.setTitle("Warframe helper");
		frmWarframeHelper.setAlwaysOnTop(true);
		frmWarframeHelper.setResizable(false);
		frmWarframeHelper.setBounds(100, 100, 450, 300);
		frmWarframeHelper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWarframeHelper.getContentPane().setLayout(null);
		
		//指挥官
		commander.setBounds(36, 71, 121, 23);
		commander.addActionListener(new cmdListener());
		frmWarframeHelper.getContentPane().add(commander);
		
		//mag吸取
		mag.setBounds(36, 32, 79, 23);
		mag.addActionListener(new magListener());
		frmWarframeHelper.getContentPane().add(mag);
		
		//启动
		startToggleButton.setSelected(false);
		startToggleButton.setBounds(294, 170, 95, 23);
		startToggleButton.addActionListener(this);
		startToggleButton.addItemListener(this);
		frmWarframeHelper.getContentPane().add(startToggleButton);
		
		//下拉菜单
		choice.setBounds(121, 32, 38, 21);
		choice.add("1");
		choice.add("2");
		choice.add("3");
		choice.add("4");
		choice.addItemListener(new dropboxListener());
		frmWarframeHelper.getContentPane().add(choice);
		
		JLabel status_1 = new JLabel("倒计时提醒");
		status_1.setBounds(233, 36, 67, 15);
		frmWarframeHelper.getContentPane().add(status_1);
		
		textField = new JTextField();
		textField.setBounds(294, 33, 38, 21);
		frmWarframeHelper.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel status_2 = new JLabel("分钟");
		status_2.setBounds(335, 36, 54, 15);
		frmWarframeHelper.getContentPane().add(status_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 217, 414, 2);
		frmWarframeHelper.getContentPane().add(separator);
		
		
		status.setFont(new Font("宋体", Font.PLAIN, 14));
		status.setBounds(20, 229, 263, 15);
		
		frmWarframeHelper.getContentPane().add(status);
		SystemTime.setBounds(294, 236, 130, 15);
		this.setTimer(SystemTime);
		frmWarframeHelper.getContentPane().add(SystemTime);

	}
	
	private void setTimer(JLabel time){
		final JLabel varTime = time;
		Timer timeAction = new Timer(1000, new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				long timemillis = System.currentTimeMillis();
				//转换日期显示格式
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				varTime.setText(df.format(new Date(timemillis)));
			}
		});
		timeAction.start();
	}
	
	
	public void itemStateChanged(ItemEvent e) {  
		
        if (startToggleButton.isSelected())
        {
        	disableall();
        	System.out.println("yes");
        	System.out.println(auto);
        }
        else 
        {
        	enableall();
        	System.out.println("no");
        	System.out.println(auto);
        }
    }  

	public void enableall()
	{
		
		mag.setEnabled(true);
		choice.setEnabled(true);
		commander.setEnabled(true);
		startToggleButton.setText("启动"); 
    	status.setText("等待");
    	auto.setStart(false);
	}
	
	public void disableall()
	{
		mag.setEnabled(false);
		choice.setEnabled(false);
		commander.setEnabled(false);
		startToggleButton.setText("正在挂机"); 
    	status.setText("启动中");
    	auto.setStart(true);
	}
	
	static class dropboxListener implements ItemListener{
		static String item = "1";
		public void itemStateChanged(ItemEvent e) {
			int stateChange = e.getStateChange();
	        item = e.getItem().toString();
	        if(stateChange == ItemEvent.SELECTED)
	        {
	        	status.setText("Mag吸取技能快捷键 "+item+" 已经设置");
	        }
	        auto.setNum(Integer.parseInt(item));
		}
		public static String select()
		{
			return item;
		}
		public static int getdropbox() //return int value for other methods
		{
			return Integer.parseInt(item);
		}
	}
	
	static class cmdListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(commander.isSelected())
			{
				auto.setCommander(true);
				status.setText("切指挥官已设置");
			}
			else
			{
				auto.setCommander(false);
				status.setText("切指挥官已取消");
			}
		}
	}
	
	
	class magListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(mag.isSelected())
			{
				choice.setEnabled(false);
				status.setText("Mag吸取已选择，快捷键为 "+ dropboxListener.select());
			}
			else
			{
				choice.setEnabled(true);
				status.setText("Mag吸取已取消");
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}



