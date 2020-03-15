package pojo;

public class User {
    private String name;   //姓名
    private int  id;	   //身份证号	
    private int number;    //预约编号
    private String telNum; //电话号码
    private int purchase;  //购买数量 
    public int getNum() {
		return number;
	}

	public void setNum(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTel(){
		return telNum;
	}
	
	public void setTel(String telNum){
		this.telNum = telNum;
	}
	
	public int getPurchase(){
		return purchase;
	}
	
	public void setPurchase(int purchase){
		this.purchase = purchase;
	}
	
	public User() {
		// TODO 自动生成的构造函数存根
	}
}
