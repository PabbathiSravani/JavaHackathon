
import java.io.*;
import java.util.*;
import org.json.simple.*;

class Read_CPU {
	public static void main(String args[]) {
		try {
			FileReader fr = new FileReader("C:\\Users\\saipa\\OneDrive\\Desktop\\CPU.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			int i = 1;
			float sum = 0;
			float max = 0;

			JSONObject values = new JSONObject();
			JSONObject finaljson = new JSONObject();
			JSONObject sampletransaction = new JSONObject();
			JSONArray finalarray = new JSONArray();
			while ((line = br.readLine()) != null) {
				String splitvalues[] = line.split("\\s+");
				float fval = Float.parseFloat(splitvalues[8]);
				if (fval > max)
					max = fval;
				sum = sum + fval;
				String seconds = i + "s";
				values.put(seconds, splitvalues[8].toString());
				i++;
			}
			float avg = (float) sum / i;
			finaljson.put("values", values);
			finaljson.put("maxcpu", (String.format("%.2f", max)));
			finaljson.put("avgcpu", (String.format("%.2f", avg)));
			sampletransaction.put("sampletransaction", finaljson);
			finalarray.add(sampletransaction);
			System.out.println(finalarray);
			FileWriter file = new FileWriter("output.json");
			file.write(finalarray.toJSONString());
			file.close();
			fr.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
