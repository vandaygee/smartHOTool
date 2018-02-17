/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthometool;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import org.json.simple.parser.JSONParser;

public final class Util {
    
     public static String makeJson(String file,String[] Attributes) throws Exception {
          String jsonFile=readFile(file, Charset.defaultCharset());
        //String jsonFile=file;
        JSONParser jParser=new JSONParser();
        org.json.simple.JSONObject newJSONObject=new org.json.simple.JSONObject();
        
        Object jsonOriginalObject=jParser.parse(jsonFile);
         
        
        org.json.simple.JSONArray jsonArray=(org.json.simple.JSONArray)jsonOriginalObject;
        
        int objectCount = jsonArray.size();       
        System.out.println("Lenght: "+objectCount);
        
        int i=0;
        for(int oCount=0;oCount < objectCount;oCount++){
           
            org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) jsonArray.get(oCount);
            org.json.simple.JSONObject jsonoObjectNew=new org.json.simple.JSONObject();
            
            org.json.simple.JSONObject innerObject=null;//=new JSONObject();
            innerObject=new org.json.simple.JSONObject();       
            String hasTimeStamp = null;
            String hasValue=null;
          
            Iterator key = jsonObject.keySet().iterator();
            int keyCount=jsonObject.size();
            
            while (key.hasNext()) { 
                i++;
                innerObject=new org.json.simple.JSONObject();
                hasTimeStamp = key.next().toString();//get timestamp
                hasValue=jsonObject.get(hasTimeStamp).toString();//get value
                   
                innerObject.put(Attributes[0],hasTimeStamp);//set timeStamp
                innerObject.put(Attributes[1],hasValue);// set value
                       
                newJSONObject.put("Temperature-"+i,innerObject );
            }
        }

        System.out.println(newJSONObject);
        
        return newJSONObject.toString();
  }
    
      public static String readFile(String path, Charset encoding) throws IOException {
       byte[] encoded = Files.readAllBytes(Paths.get(path));
       return new String(encoded, encoding);
    }
    
}
