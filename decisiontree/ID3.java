package decisiontree;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import Main.Matrix;

public class ID3 {
	
	public ID3 () { }
	
	public void findTree(Matrix examples, Matrix targetAttributes) {
		Node root = runID3(examples, targetAttributes, getAllAttributes(examples));
	}
	
	// this set does not include the target attribute
	private LinkedHashSet<Attribute> getAllAttributes(Matrix examples) {
		LinkedHashSet<Attribute> attributes = new LinkedHashSet<Attribute>();
		for (int i = 0; i < examples.cols(); i++) {
			Attribute attribute = new Attribute(examples.attrName(i), getValuesOfAttribute(examples, i));
			attributes.add(attribute);
		}
		return attributes;
	}
	
	public Node runID3(Matrix examples, Matrix targetAttributes, LinkedHashSet<Attribute> attributes) {
		Node root = new Root();
		if (allExamplesPositive(targetAttributes) || attributes.size() == 0) {
			System.out.println("No variance in examples...returning Root with label = most common classification");
			Label label = new Label(targetAttributes.attrValue(0, 0));
			root.setLabel(label);
			return root;
		}
		for (Attribute attr : attributes)
			System.out.println("Size of attribute: " + attr.getNumberOfValues());
		
//		for (String value : )
		return null;
	}
	
	private boolean allExamplesPositive(Matrix targetAttribute) {
		return targetAttribute.columnMax(0) == targetAttribute.columnMin(0);
	}
	
	private Attribute findBestAttribute(Matrix examples, Matrix targetAttributes) {
		int c_wise_classification = targetAttributes.valueCount(0);
		
		return null;
	}
	
	private double getEntropy(Matrix data) {
		
	}
	
	// attributes can either be target attributes or examples
	private LinkedHashSet<String> getValuesOfAttribute(Matrix attributes, int attribute) {
		LinkedHashSet<String> values = new LinkedHashSet<String>();
		int numberofValues = attributes.valueCount(attribute);
		for (int i = 0; i < attributes.rows(); i++) {
			double whichValue = attributes.get(i, attribute);
			values.add(attributes.attrValue(attribute, (int) whichValue));
			if (values.size() == numberofValues)
				break;
		}
		return values;
	}
}
