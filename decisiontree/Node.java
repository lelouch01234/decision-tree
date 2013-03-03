package decisiontree;

public abstract class Node {
	
	private Label _label;
	
	public Node () {
	}
	
	public Label getLabel() {
		return _label;
	}
	
	public void setLabel(Label label) {
		_label = label;
	}

	public abstract void setAttribute(Attribute bestAttribute);

	public abstract void addBranch(int value, Node leaf);
}
