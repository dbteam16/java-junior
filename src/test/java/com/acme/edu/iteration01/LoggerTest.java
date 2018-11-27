package com.acme.edu.iteration01;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static java.lang.System.lineSeparator;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion

    @Test
    public void shouldLogInteger() throws IOException {
        //region when
        Logger.log(1);
        Logger.flush();
        Logger.log(0);
        Logger.flush();
        Logger.log(-1);
        Logger.flush();
        //endregion

        String ls = lineSeparator();

        //region then
        assertSysoutEquals("primitive: 1" + ls + "primitive: 0" + ls + "primitive: -1" + ls);
        //endregion
    }

    @Test
    public void shouldLogByte() throws IOException {
        //region when
        Logger.log((byte)1);
        Logger.flush();
        Logger.log((byte)0);
        Logger.flush();
        Logger.log((byte)-1);
        Logger.flush();
        //endregion

        //region then
        assertSysoutContains("primitive: 1");
        assertSysoutContains("primitive: 0");
        assertSysoutContains("primitive: -1");
        //endregion
    }

    @Test
    public void shouldLogChar() throws IOException {
        //region when
        Logger.log('a');
        Logger.log('b');
        //endregion

        //region then
        assertSysoutContains("char: ");
        assertSysoutContains("a");
        assertSysoutContains("b");
        //endregion
    }
    @Test
    public void shouldLogString() throws IOException {
        //region when
        Logger.log("test string 1");
        Logger.log("other str");
        Logger.flush();
        //endregion

        //region then
        assertSysoutContains("string: test string 1");
        assertSysoutContains("string: other str");
        //endregion
    }

    @Test
    public void shouldLogBoolean() throws IOException {
        //region when
        Logger.log(true);
        Logger.log(false);
        //endregion

        //region then
        assertSysoutContains("true");
        assertSysoutContains("false");
        //endregion
    }


    @Test
    public void shouldLogReference() throws IOException {
        //region when
        Logger.log(new Object());
        //endregion

        //region then
        assertSysoutContains("reference: ");
        assertSysoutContains("@");
        //endregion
    }
}