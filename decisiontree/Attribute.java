package decisiontree;

import java.util.HashSet;
import java.util.Set;

public class Attribute {
	
	private String _name;
	private Set<String> _values;
	
	public Attribute(String name, HashSet<String> values) {
		_name = name;
		_values = values;
	}
	
	public String getName() {
		return _name;
	}
	
	public int getNumberOfValues() {
		return _values.size();
	}
}
