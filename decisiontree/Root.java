package decisiontree;

import java.util.HashSet;
import java.util.Set;

public class Root extends Node {
	
	private Set<Branch<String, Node>> _branches;
	private Attribute _attribute;
	
	public Root() {
		_branches = new HashSet<Branch<String, Node>>();
	}
	
	public void setAttribute(Attribute attribute) {
		_attribute = attribute;
	}
	
	public void addBranch(String value, Node node) {
		Branch branch = new Branch(value, node);
		_branches.add(branch);
	}
}
