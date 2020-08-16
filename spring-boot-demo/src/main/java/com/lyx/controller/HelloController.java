package com.lyx.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class HelloController {

    @RequestMapping("hello")
    public String helloWorld(){
        return "hello Java";
    }

    @RequestMapping("stream")
    public void streamTest(){
        List<String> list = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");

        long count = list.stream().filter(s -> s.isEmpty()).count();
        System.out.println("空字符串数量为 = " + count);

        long count1 = list.stream().filter(s -> s.length() == 3).count();
        System.out.println("字符长度为3的数量为 = " + count1);

        List<String> collect = list.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        System.out.println("非空集合为 = " + collect);

        String collect1 = list.stream().filter(s -> !s.isEmpty()).collect(Collectors.joining(","));
        System.out.println("合并后的字符串 = " + collect1);

        List<Integer> integers = Arrays.asList(1,2,13,4,15,6,17,8,19);

        List<Integer> collect2 = integers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println("平方后的数组 = " + collect2);

        IntSummaryStatistics intSummaryStatistics = integers.stream().mapToInt(i -> i).summaryStatistics();
        System.out.println("数组中最大值 = " + intSummaryStatistics.getMax());
        System.out.println("数组中最小值 = " + intSummaryStatistics.getMin());
        System.out.println("数组中元素个数 = " + intSummaryStatistics.getCount());

        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);

        List<String> collect3 = list.parallelStream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        System.out.println("collect3 = " + collect3);

        Object collect4 = integers.parallelStream().filter(i -> i > 0).collect(Collectors.toList());

    }


    @RequestMapping("success")
    public String success(HashMap<String,Object> map, HttpServletRequest httpRequest, HttpServletResponse response){
        map.put("hello","你好");
        HttpSession session = httpRequest.getSession();

        session.setAttribute("111","222");
        System.out.println(httpRequest.getClass());

        return "success";
    }
}
