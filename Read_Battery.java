
import java.io.*;
import java.util.*;
import org.json.simple.*;

class Read_Battery {
	public static void main(String args[]) {
		try {
			FileReader fr = new FileReader("C:\\Users\\saipa\\OneDrive\\Desktop\\Battery.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			JSONObject values = new JSONObject();
			String activities = null;
			String drain = null;
			while ((line = br.readLine()) != null) {
				if (line.contains("Foreground activities:")) {
					activities = line.replaceAll("    Foreground activities: ", "");
				}

				if (line.contains("Uid u0a202:")) {
					String splitvalues[] = line.split("\\s+");
					drain = splitvalues[3];
				}
			}

			values.put("Foreground_time", activities);
			values.put("Battery_percentage", (Float.parseFloat(drain) / 1000));
			values.put("Battery_drain", drain);
			System.out.println(values);
			FileWriter file = new FileWriter("output1.json");
			file.write(values.toJSONString());
			file.close();
			fr.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
