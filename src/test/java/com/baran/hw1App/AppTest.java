package com.baran.hw1App;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void testGivenEmptyListThenReturnsNegativeDelegates(){
        ArrayList<Integer>arr1= new ArrayList<>(Arrays.asList());
        ArrayList<Integer>arr2= new ArrayList<>(Arrays.asList());
        int numOfDelsPerDist=5;
        boolean popularVoteFlag=false;

        Integer[] expected=new Integer[]{-3,-3};
        Integer[] actual=new App().delegateCount(arr1,arr2,numOfDelsPerDist,popularVoteFlag);
        assertEquals(expected[0],actual[0]);
        assertEquals(expected[1],actual[1]);
    }

    public void testGivenNonPositiveNumOfDelegatesPerDistrictThenReturnsNegativeDelegates(){
        ArrayList<Integer>arr1= new ArrayList<>(Arrays.asList(120,232,43));
        ArrayList<Integer>arr2= new ArrayList<>(Arrays.asList(1100,231,40));
        int numOfDelsPerDist=0;
        boolean popularVoteFlag=false;

        Integer[] expected=new Integer[]{-2,-2};
        Integer[] actual=new App().delegateCount(arr1,arr2,numOfDelsPerDist,popularVoteFlag);
        assertEquals(expected[0],actual[0]);
        assertEquals(expected[1],actual[1]);
    }

    public void testGivenUnequalArraysThenReturnsNegativeDelegates(){
        ArrayList<Integer>arr1= new ArrayList<>(Arrays.asList(120,232,43,32));
        ArrayList<Integer>arr2= new ArrayList<>(Arrays.asList(1100,231,40));
        int numOfDelsPerDist=5;
        boolean popularVoteFlag=false;

        Integer[] expected=new Integer[]{-1,-1};
        Integer[] actual=new App().delegateCount(arr1,arr2,numOfDelsPerDist,popularVoteFlag);
        assertEquals(expected[0],actual[0]);
        assertEquals(expected[1],actual[1]);
    }

    public void testGivenNormalSetWithFalseFlagThenOutputsCorrectResults(){
        ArrayList<Integer>arr1= new ArrayList<>(Arrays.asList(120,232,43));
        ArrayList<Integer>arr2= new ArrayList<>(Arrays.asList(1100,231,40));
        int numOfDelsPerDist=1;
        boolean popularVoteFlag=false;

        Integer[] expected=new Integer[]{2,1};
        Integer[] actual=new App().delegateCount(arr1,arr2,numOfDelsPerDist,popularVoteFlag);
        assertEquals(expected[0],actual[0]);
        assertEquals(expected[1],actual[1]);
    }

    public void testGivenNormalEqualSetWithFalseFlagThenOutputsCorrectResults(){
        ArrayList<Integer>arr1= new ArrayList<>(Arrays.asList(120,232,43));
        ArrayList<Integer>arr2= new ArrayList<>(Arrays.asList(120,232,43));
        int numOfDelsPerDist=1;
        boolean popularVoteFlag=false;

        Integer[] expected=new Integer[]{0,0};
        Integer[] actual=new App().delegateCount(arr1,arr2,numOfDelsPerDist,popularVoteFlag);
        assertEquals(expected[0],actual[0]);
        assertEquals(expected[1],actual[1]);
    }

    public void testGivenNormalSetWithTrueFlagThenOutputsCorrectResults(){
        ArrayList<Integer>arr1= new ArrayList<>(Arrays.asList(120,232,43));
        ArrayList<Integer>arr2= new ArrayList<>(Arrays.asList(1100,231,40));
        int numOfDelsPerDist=1;
        boolean popularVoteFlag=true;

        Integer[] expected=new Integer[]{1,2};
        Integer[] actual=new App().delegateCount(arr1,arr2,numOfDelsPerDist,popularVoteFlag);
        assertEquals(expected[0],actual[0]);
        assertEquals(expected[1],actual[1]);
    }

    public void testGivenNormalSetWithFalseFlagAndNumerousDelegatesPerDistrictThenOutputsCorrectResults(){
        ArrayList<Integer>arr1= new ArrayList<>(Arrays.asList(120,232,43));
        ArrayList<Integer>arr2= new ArrayList<>(Arrays.asList(1100,231,40));
        int numOfDelsPerDist=5;
        boolean popularVoteFlag=false;

        Integer[] expected=new Integer[]{10,5};
        Integer[] actual=new App().delegateCount(arr1,arr2,numOfDelsPerDist,popularVoteFlag);
        assertEquals(expected[0],actual[0]);
        assertEquals(expected[1],actual[1]);
    }

    public void testGivenNormalSetWithTrueFlagAndNumerousDelegatesPerDistrictThenOutputsCorrectResults(){
        ArrayList<Integer>arr1= new ArrayList<>(Arrays.asList(120,232,43));
        ArrayList<Integer>arr2= new ArrayList<>(Arrays.asList(1100,231,40));
        int numOfDelsPerDist=5;
        boolean popularVoteFlag=true;

        Integer[] expected=new Integer[]{3,12};
        Integer[] actual=new App().delegateCount(arr1,arr2,numOfDelsPerDist,popularVoteFlag);
        assertEquals(expected[0],actual[0]);
        assertEquals(expected[1],actual[1]);
    }
}
