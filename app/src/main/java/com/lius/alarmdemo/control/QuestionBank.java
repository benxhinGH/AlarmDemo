package com.lius.alarmdemo.control;

import com.lius.alarmdemo.model.Question;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class QuestionBank {
    public static Question generateQuestion(){
        int[] addends=new int[20];
        int sum=0;
        StringBuilder subject=new StringBuilder();
        Random random=new Random();
        for(int a:addends){
            a=random.nextInt(100);
            sum+=a;
            subject.append(a+"+");
        }
        subject.replace(subject.length()-1,subject.length(),"=");

        return new Question(subject.toString(),sum);
    }

}
