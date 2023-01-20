package DataDriven_Framework.Datadriven;

import java.util.ArrayList;

public class testSample {
	public static void main(String[] args) throws Throwable {
		Excel_dataDriven edd = new Excel_dataDriven();
		ArrayList<String> data = edd.getData("Delete profile");
		System.out.println(data.get(0));
		System.out.println(data.get(1));
		System.out.println(data.get(2));
		System.out.println(data.get(3));

	}

}
