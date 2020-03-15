package information;

import java.sql.Connection;
import java.sql.Statement;

public class Input {

    DBUtil tool = new DBUtil();
    public Connection c;
    public boolean exeSQL(String sql){
        try{
            c=tool.getConnection();
            if(c==null)
                return false;
            Statement s=c.createStatement( ); // 创建SQL语句对象
            s.executeUpdate(sql);
            s.close();
            return true;
        }catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //插入数据
    public void input(String name,String id,String phone,int num){
        //插入表二数据
        /*exeSQL("insert into appointment_list values(null,'" + name + "','" +
                id + "','" + phone + "','" + num + "','" + 1 + "')");*/
        //System.out.println(name+id+phone+num);//测试
    }

    public static void main(String[] args) {
        isRidcard id=new isRidcard();
        isRphone pnum=new isRphone();
        Input it=new Input();

        //输入的数据
        String str0="cj";
        String str1="350301199810051816";
        String str2="13215007537";
        int str3=1;
        if(id.isRid(str1)){
            if(pnum.isRnum(str2)){
                it.input(str0,str1,str2,str3);
            }
            else
                System.out.println("电话号码错误");
        }
        else
            System.out.println("身份证错误");

    }



}
