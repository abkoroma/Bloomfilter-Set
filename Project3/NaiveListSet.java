//Abu Koroma
// 1908-111-3

import java.util.ArrayList;

public class NaiveListSet implements Set {
	
	ArrayList<String> list;
	
	public NaiveListSet() {
		list = new ArrayList<String>();
	}

	@Override
	public void add(String element) {
		list.add(element);
	}

	@Override
	public boolean contains(String element) {
		return list.contains(element);
	}	

}
