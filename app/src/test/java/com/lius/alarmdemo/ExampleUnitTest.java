package com.lius.alarmdemo;

import android.os.Environment;

import com.lius.alarmdemo.control.QuestionBank;
import com.lius.alarmdemo.model.Question;

import org.junit.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        String soundPath= Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"BackInTime.mp3";
        System.out.println(soundPath);
    }
}