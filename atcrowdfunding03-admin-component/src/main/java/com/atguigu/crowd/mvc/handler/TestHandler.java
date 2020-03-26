package com.atguigu.crowd.mvc.handler;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.atguigu.crowd.entity.ParamData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author swwan
 * @create 2020-03-26 20:23
 */
@Controller
public class TestHandler {


    private static final String SUCCESS = "success";

    @RequestMapping("/test/aaa.html")
    public String doTest() {
        return "target";
    }

    @ResponseBody
    @RequestMapping("/send/array/three.html")
    public String testReceiveArrayThree(@RequestBody List<Integer> array) {
        for (Integer number : array) {
            System.out.println("number=" + number);
        }
        return SUCCESS;
    }

    @ResponseBody
    @RequestMapping("/send/array/two.html")
    public String testReceiveArrayTwo(ParamData paramData) {
        List<Integer> array = paramData.getArray();
        for (Integer number : array) {
            System.out.println("number=" + number);
        }
        return SUCCESS;
    }


    @ResponseBody
    @RequestMapping("/send/array/one.html")
    public String testReceiveArrayOne(@RequestParam("array[]") List<Integer> array) {
        for (Integer number : array) {
            System.out.println("number=" + number);
        }
        return SUCCESS;
    }

}
