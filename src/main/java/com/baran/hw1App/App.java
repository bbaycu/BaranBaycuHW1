package com.baran.hw1App;

import java.util.ArrayList;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public static Integer[] delegateCount(ArrayList<Integer> candidate1Votes,ArrayList<Integer> candidate2Votes,
                                          int numOfDelegatesPerDistrict,boolean isPopularVoteCount){

        Integer[] out=new Integer[2];

        if(candidate1Votes.size()!=candidate2Votes.size()) return new Integer[]{-1,-1}; //Hatalı durumda negatif delege sayısı döner
        if(numOfDelegatesPerDistrict<=0) return new Integer[]{-2,-2};   //Hatalı durumda negatif delege sayısı döner
        if(candidate1Votes.size()==0||candidate2Votes.size()==0) return new Integer[]{-3,-3};   //Hatalı durumda negatif delege sayısı döner
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
