package com.aurensanx.runtempocalculator;

import org.junit.Assert;
import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest extends MainActivity {
    @Test
    public void calculateResultTest() throws Exception {
        Assert.assertArrayEquals(new long[2], calculateResult(new double[2]));
        Assert.assertArrayEquals(new long[2], calculateResult(new double[4]));
        Assert.assertArrayEquals(new long[]{4, 0}, calculateResult(new double[]{0, 40, 0, 10}));
        Assert.assertArrayEquals(new long[]{4, 38}, calculateResult(new double[]{0, 46, 20, 10}));
        Assert.assertArrayEquals(new long[]{3, 49}, calculateResult(new double[]{1, 20, 0, 21}));
        Assert.assertArrayEquals(new long[]{4, 16}, calculateResult(new double[]{3, 0, 0, 42.195}));
    }
}