package pojo;

public class Appointer {
	int id;//预约编号
	int subscribe;//需求口罩数量
	boolean isLottery;
	
	public Appointer(int id,int subscribe)
	{
		this.id=id;
		this.subscribe=subscribe;
		this.isLottery=false;
	}

	public void lottery()
	{
		this.isLottery=true;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public int getSubscribe()
	{
		return this.subscribe;
	}
	
	public boolean getIsLottery()
	{
		return this.isLottery;
	}
}
