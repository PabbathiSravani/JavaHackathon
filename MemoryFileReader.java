package memoryparser;

import java.io.*;
import java.util.*;
import org.json.simple.*;

class MemoryFileReader{
	public static void main(String args[]){
		try{
		FileReader fr = new FileReader("memory.txt");
		BufferedReader br=new BufferedReader(fr);
		String line; 
		int i=1;
		int sum = 0;
		int max = 0;
		
		JSONObject values = new JSONObject();
		JSONObject finaljson = new JSONObject();
		while((line=br.readLine())!=null){
			if(line.contains(":"))
			{
				line = line.replaceAll("\\s+","");
				line = line.replaceAll("TOTALSWAPPSS:0","");
				line = line.replaceAll("TOTAL:","");
				int conline = Integer.parseInt(line);
				float fval =Float.parseFloat(line);
				if(conline>max)
					max = conline;
				sum = sum + conline;
				String seconds = i+"s";
				float num = fval/1000;
				values.put(seconds,(String.format("%.2f",num)));
				i++;	
			}
		}
		float avg =(float)sum / i ; 
		/*System.out.println(max);
		System.out.println(avg);
		System.out.println(values);*/
		float fmax = (float)max;
		finaljson.put("AverageMemory(MB)", (String.format("%.2f",avg/1000)));
		finaljson.put("values", values);
		finaljson.put("MaxMemory(MB)", (String.format("%.2f",fmax/1000)));
		finaljson.put("Usecasename", "HomePage");
		System.out.println(finaljson);
		FileWriter file = new FileWriter("output.json");
        file.write(finaljson.toJSONString());
        file.close();
		fr.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
