package test;

import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 13:58 2017/12/28
 * @Modified_By:
 */
public
class JsTest {

    public static void main (String[] args) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        try {
            String path = JsTest.class.getResource("").getPath();
            System.out.println(path);
            engine.eval(new FileReader(path + "test.Js.js"));
            if (engine instanceof Invocable) {
                Invocable invocable = (Invocable) engine;
                Js executeMethod = invocable.getInterface(Js.class);
                System.out.println(executeMethod.execute(39.90469, 116.407173));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

