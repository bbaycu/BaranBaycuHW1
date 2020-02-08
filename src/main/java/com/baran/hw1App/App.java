package com.baran.hw1App;

import java.util.ArrayList;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public static Integer[] delegateCount(ArrayList<Integer> candidate1Votes,ArrayList<Integer> candidate2Votes,
                                          int numOfDelegatesPerDistrict,boolean isPopularVoteCount) throws Exception{

        Integer[] out=new Integer[2];

        if(candidate1Votes.size()!=candidate2Votes.size()) throw new Exception("Bölge sayıları eşit olmalı");
        if(numOfDelegatesPerDistrict<=0) throw new Exception("Delege sayısı pozitif olmalı");
        int c1=0,c2=0;

        if (!isPopularVoteCount){
            for(int i=0;i<candidate1Votes.size();i++){
                if(candidate1Votes.get(i)>candidate2Votes.get(i))       c1++;
                else if(candidate1Votes.get(i)<candidate2Votes.get(i))  c2++;
            }
            out=new Integer[]{c1*numOfDelegatesPerDistrict,c2*numOfDelegatesPerDistrict};
        }
        else{
            int numOfDistricts=candidate1Votes.size();
            for(int i=0;i<numOfDistricts;i++){
                c1=c1+candidate1Votes.get(i);
                c2=c2+candidate2Votes.get(i);
                int numOfDelegates=numOfDistricts*numOfDelegatesPerDistrict;
                int c1Dels=Math.round((float)c1/(float)(c1+c2)*numOfDelegates);
                out=new Integer[]{c1Dels,numOfDelegates-c1Dels};
            }
        }
        return out;
    }
}
