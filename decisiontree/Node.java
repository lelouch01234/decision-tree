package decisiontree;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;

public class Node {
	
	private Label _label;
	private LinkedHashMap<Double, Node> _branches;
	private Attribute _attribute;
	private int _nodeID;
	
	public Node (int nodeCounter) {
		_label = new Label();
		_branches = new LinkedHashMap<Double, Node>();
		_attribute = new Attribute();
		_nodeID = nodeCounter;
	}
	
	public int getNodeID() {
		return _nodeID;
	}
	
	public Label getLabel() {
		return _label;
	}
	
	public void setLabel(Label label) {
		_label = label;
	}

	public void setAttribute(Attribute bestAttribute) {
		_attribute = bestAttribute;
	}
	
	public void addBranch(double value, Node node) {
		System.out.println("");
		_branches.put(value, node);
	}
	
	public LinkedHashMap<Double, Node> getBranches() {
		return _branches;
	}
	
	public Attribute getAttribute() {
		return _attribute;
	}
	
	public void dumpDot() throws IOException {
		PrintWriter out = new PrintWriter(new File("output/tree.dot"));
		out.println("digraph DecisionTree {");
		out.println("graph [ordering=\"out\"];");
		dumpDot(out);
		out.println("}");
		out.close();
	}
	
	private void dumpDot(PrintWriter out) {
		String myLabel = "";
		if (_branches.isEmpty()) {
			myLabel = _label.getStrValue();
		}
		out.println("  " + _nodeID + " [label=\"" + myLabel + "\"];");//" + toString() + "\"];\n");
		
		if (!_branches.isEmpty()) {
			for (double key : _branches.keySet()) {
				Node childNode = _branches.get(key);
				String edgeLabel = "";
				edgeLabel = childNode.getBranches().get(key).getAttribute().getName();
				int childNodeID = childNode.getNodeID();
				out.print("  " + _nodeID + " -> " + childNodeID);
				out.print(" [label=\" " +edgeLabel + "\"];\n");
				childNode.dumpDot(out);
			}
		}
	}
}
