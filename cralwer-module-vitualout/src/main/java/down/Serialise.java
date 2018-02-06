package down;

import profile.ConfigCache;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 21:22 2018/1/1
 * @Modified_By:
 */
public
class Serialise {
    public static void main (String[] args) {
        List<String> test = new LinkedList<String>();

        System.out.println((List<String>)UnSerialise.readerObject(ConfigCache.NO_FIND_CITY));
    }

    public static void write(Map map , String fileName) {
        File staInfo = new File(fileName);
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(staInfo));
            oos.writeObject(map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.flush();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void writeObject(Object ob, String fileName) {
        File staInfo = new File(fileName);
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(staInfo));
            oos.writeObject(ob);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.flush();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
