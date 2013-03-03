package decisiontree;

public class Label {
	
	private String _strvalue;
	private double _value;
	
	public Label(String strvalue, double value) {
		_strvalue = strvalue;
		_value = value;
	}
	
	public String getStrValue() {
		return _strvalue;
	}
	
	public double getValue() {
		return _value;
	}
}
