import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class auto extends Thread {
	private boolean start;
	private boolean commander;
	private int num;
	private int cmdshiftcd, ordercd, magcd;
	Random ra = new Random();
	public auto(boolean start, int num, boolean commander) 
	{
		super();
		this.start = start;
		this.commander = commander;
		this.num = num;
	}

	@Override
	public String toString() {
		return "auto [start=" + start + ", commander=" + commander + ", num=" + num + "]";
	}

	public void SendKeyDelay(Robot r,int keycode)
	{
		int randomnum = ra.nextInt(500) + 700;
		r.keyPress(keycode);
		r.delay(randomnum);
		r.keyRelease(keycode);
	}
	
	public void runauto() throws InterruptedException, AWTException
	{
		Robot robot = new Robot();
		while(true)
		{
			if(this.start == true)
			{
				if(commander == true)
				{
					SendKeyDelay(robot, KeyEvent.VK_5); 
					mainpage.status.setText("切指挥官");
					robot.delay(3000);
					SendKeyDelay(robot, KeyEvent.VK_5); 
				}
				if(mainpage.mag.isSelected())
				{
					mainpage.status.setText("准备吸取");
					robot.delay(5000);
					switch(num) 
					{
					
						case 1:
							SendKeyDelay(robot, KeyEvent.VK_1);
							break;
						case 2:
							SendKeyDelay(robot, KeyEvent.VK_2);
							break;
						case 3:
							SendKeyDelay(robot, KeyEvent.VK_3);
							break;
						case 4:
							SendKeyDelay(robot, KeyEvent.VK_4);
							break;
					}
					mainpage.status.setText("使用 " + num + " 吸取");
				}
				robot.delay(35000);//45秒
			}
			else if(this.start == false)
			{
				robot.delay(2000);
//				System.out.println("not started");
			}
		}
	}
	
	public void run() {
		try {
			runauto();
		} catch (InterruptedException | AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isCommander() {
		return commander;
	}

	public void setCommander(boolean commander) {
		this.commander = commander;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
