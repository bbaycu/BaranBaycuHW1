package com.baran.hw1App;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;




public class App 
{
    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Hello, World");

        post("/compute", (req, res) -> {
            //System.out.println(req.queryParams("input1"));
            //System.out.println(req.queryParams("input2"));
            String input1 = req.queryParams("input1");
            java.util.Scanner sc1 = new java.util.Scanner(input1);
            sc1.useDelimiter("[;\r\n]+");
            java.util.ArrayList<Integer> inputList = new java.util.ArrayList<>();
            while (sc1.hasNext())
            {
                int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
                inputList.add(value);
            }

            String input2 = req.queryParams("input1");
            java.util.Scanner sc2 = new java.util.Scanner(input1);
            sc1.useDelimiter("[;\r\n]+");
            java.util.ArrayList<Integer> inputList2= new java.util.ArrayList<>();
            while (sc1.hasNext())
            {
                int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
                inputList2.add(value);
            }

            String input3 = req.queryParams("input3").replaceAll("\\s","");
            int input3AsInt = Integer.parseInt(input3);

            String input4 = req.queryParams("input4").replaceAll("\\s","");
            boolean input4Flag=false;
            if(input4.toLowerCase()=="t"||input4.toLowerCase()=="true"||input4.toLowerCase()=="yes"||input4.toLowerCase()=="y") input4Flag=true;
            if(input4.toLowerCase()=="f"||input4.toLowerCase()=="false"||input4.toLowerCase()=="no"||input4.toLowerCase()=="n") input4Flag=false;

            Integer[] result = App.delegateCount(inputList, inputList2,input3AsInt,input4Flag);

            Map map = new HashMap<>();
            map.put("result", ""+result[0]+","+result[1]);
            return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());


        get("/compute",
                (rq, rs) -> {
                    Map map = new HashMap();
                    map.put("result", "not computed yet!");
                    return new ModelAndView(map, "compute.mustache");
                },
                new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
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
