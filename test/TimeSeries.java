package test;
import java.util.*;
import java.io.*;

public class TimeSeries {

	public Map<String, List<String>> columns = new HashMap<String, List<String>>();
	public List<String> columnNames = new ArrayList<String>();

	public TimeSeries(String csvFileName) {
		//column names
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(csvFileName));

			//header flag
			boolean header = true;

			while ((sCurrentLine = br.readLine()) != null) {
				String[] fields = sCurrentLine.split(",");

				for (int i = 0; i < fields.length; i++) {
					if (header) {
						//array list as null, since we don't have values yet
						columns.put(fields[i], null);

						//also seperately store the column names to be used to as key to get the list
						columnNames.add(fields[i]);

						//reached end of the header line, set header to false
						if (i == (fields.length - 1))
							header = false;
					} else {
						//retrieve the list using the column names
						List<String> tempList = columns.get(columnNames.get(i));

						if (tempList != null) {
							//if not null then already element there
							tempList.add(fields[i]);
						} else {
							//if null then initialize the list
							tempList = new ArrayList<String>();
							tempList.add(fields[i]);
						}
						//add modified list back to the map
						columns.put(columnNames.get(i), tempList);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public float[] stringToFloat( List<String> stringList ) {
	float[] floatArray = new float[stringList.size()];
	Iterator<String> iterator = stringList.listIterator();
	for (int i = 0; i < stringList.size() ; i++) {
			floatArray[i] = Float.parseFloat(iterator.next());
		}
	return floatArray;
	}
}
