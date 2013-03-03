package decisiontree;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Attribute {
	
	private String _name;
	private LinkedHashSet<Integer> _values;
	private int _columnPosition;
	
	public Attribute(String name, LinkedHashSet<Integer> values, int columnPosition) {
		_name = name;
		_values = values;
		_columnPosition = columnPosition;
	}
	
	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public LinkedHashSet<Integer> get_values() {
		return _values;
	}

	public void set_values(LinkedHashSet<Integer> values) {
		this._values = values;
	}

	public int get_columnPosition() {
		return _columnPosition;
	}

	public void set_columnPosition(int _columnPosition) {
		this._columnPosition = _columnPosition;
	}

	public String getName() {
		return _name;
	}
	
	public int getNumberOfValues() {
		return _values.size();
	}
}
