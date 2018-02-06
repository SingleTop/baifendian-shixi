package down;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 21:19 2018/1/1
 * @Modified_By:
 */
public
class UnSerialise {
    public static void main (String[] args) {
        BaiduWaimaiDown.shopSerialise test = BaiduWaimaiDown.readShopDownCtr();
        test.setTime0(0);
        test.setTime1(0);
        test.setTime2(0);
        test.setTime3(0);
BaiduWaimaiDown.writeShopDownCtr(test);
        System.out.println(test);

    }


    public static
    Map reader(String fileName){
        Map<String, String> map = new HashMap<String, String>();
        File staInfo = new File (fileName);
        int flag = 0;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(staInfo));
            Object o = ois.readObject();
            map = (Map<String, String>)o;
            flag = 1;
            //System.out.println(map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return  null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }finally{
            if(flag == 1){
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            }
        }
        return map;
    }
public static Object readerObject(String fileName){
    Object object = null;
    File staInfo = new File (fileName);
    ObjectInputStream ois = null;
    try {
        ois = new ObjectInputStream(new FileInputStream(staInfo));
        Object o = ois.readObject();
        object = o;

        //System.out.println(map);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }finally{
        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    return object;
}

}
