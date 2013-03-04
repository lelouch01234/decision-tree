package decisiontree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import Main.Matrix;

public class ID3 {
	
	private TableManager _tableManager;
	private int _nodeCounter;
	
	public ID3 () {
		_nodeCounter = 0;
	}
	
	public Node buildTree (Matrix examples, Matrix targetAttributes) {
		LinkedHashSet<Attribute> attributes = createAllAttributes(examples);
		_tableManager = new TableManager(examples, targetAttributes, attributes);
		Node root = runID3(examples, targetAttributes, attributes);
		return root;
	}
	
	// this set does not include the target attribute
	private LinkedHashSet<Attribute> createAllAttributes (Matrix examples) {
		LinkedHashSet<Attribute> attributes = new LinkedHashSet<Attribute>();
		for (int i = 0; i < examples.cols(); i++) {
			Attribute attribute = new Attribute(examples.attrName(i), getValuesOfAttributeFromCurrentTable(examples, i), i);
			attributes.add(attribute);
		}
		return attributes;
	}
	
	// attributes can either be target attributes or examples
	private LinkedHashSet<Double> getValuesOfAttributeFromCurrentTable (Matrix attributes, int attribute) {
		LinkedHashSet<Double> values = new LinkedHashSet<Double>();
		int numberofValues = attributes.valueCount(attribute);
		for (int i = 0; i < attributes.rows(); i++) {
			values.add(attributes.get(i, attribute));
			if (values.size() == numberofValues)
				break;
		}
		return values;
	}
	
	public Node runID3 (Matrix examples, Matrix targetAttributes, LinkedHashSet<Attribute> attributes) {
		Node root = new Node(_nodeCounter);
		_nodeCounter++;
		if (allExamplesPositive(targetAttributes) || attributes.isEmpty()) {
			Label label = new Label(targetAttributes.attrValue(0, 0), (int)targetAttributes.get(0, 0));
			root.setLabel(label);
		}
		else {
			Attribute A = findBestAttribute();
			root.setAttribute(A);
			System.out.println("Attribute= " + A.getName());
			for (double value : A.getValues()) {
				Matrix[] examples_vi = _tableManager.getTrimmedMatrices(A.getColumnPositionID(), (int)value);
				if (examples_vi[0].rows() < 1) {
					Node leafNode = new Node(_nodeCounter);
					_nodeCounter++;
					double mcv = targetAttributes.mostCommonValue(0);
					Label label = new Label(targetAttributes.attrValue(0, (int)mcv), (int)mcv);
					leafNode.setLabel(label);
					root.addBranch(value, leafNode);
				}
				else {
					attributes.remove(A);
					_tableManager.set_attributes(attributes);
					_tableManager.set_examples(examples_vi[0]);
					_tableManager.set_targetAttributes(examples_vi[1]);
//					examples_vi[0].print();
					root.addBranch(value, runID3(examples_vi[0], examples_vi[1], attributes));
				}
			}
		}
		return root;
	}
	
	private boolean allExamplesPositive(Matrix targetAttribute) {
		return targetAttribute.columnMax(0) == targetAttribute.columnMin(0);
	}
	
	// algorithm for finding best attribute is based on Gain (S, A)
	private Attribute findBestAttribute () {
		double bestGain = -10;
		int bestAttribute = -10;
		System.out.println(_tableManager.get_attributes().toString());
		for (Attribute attribute : _tableManager.get_attributes()) {
			int attributeColPos = attribute.getColumnPositionID();
			double gain = calculateGain(attributeColPos);
			System.out.println("Testing Node" + attributeColPos);
			if (bestGain <= gain) {
				bestGain = gain;
				bestAttribute = attributeColPos;
			}
		}
		for (Attribute attribute : _tableManager.get_attributes()) {
//			System.out.println(attribute.get_columnPosition() + "==" + bestAttribute);
			
			if (attribute.getColumnPositionID() == bestAttribute)
				return attribute;
		}
		return null;
	}
	
	private double calculateGain (int attribute) {
		ArrayList<int[]> valueOccurrences = _tableManager.getValueOccurrences(attribute);
		int[] targetOccurrences = _tableManager.getTargetAttributeOccurrences();
		double totalOccurrences = 0;
		for (int i = 0; i < targetOccurrences.length; i++) {
			totalOccurrences += targetOccurrences[i];
		}
		double[] valueSummation = new double[valueOccurrences.size()];
		for (int i = 0; i < valueOccurrences.size(); i++) {
			for (int j = 0; j < targetOccurrences.length; j++) {
				valueSummation[i] += valueOccurrences.get(i)[j];
			}
		}
		double value = 0;
		for (int i = 0; i < valueOccurrences.size(); i++) {
			value += (-1) * (valueSummation[i] / totalOccurrences) * calculateEntropy(valueOccurrences.get(i));
		}
		double gain = calculateEntropy(targetOccurrences) + value;
		return gain;
	}
	
	private double calculateEntropy (int[] occurrences) {
		double totalOccurrences = 0;
		for (int i = 0; i < occurrences.length; i ++) {
			totalOccurrences += (double)occurrences[i];
		}
		double entropy = 0;
		for (int i = 0; i < occurrences.length; i++) {
			entropy += (-1) * (occurrences[i]/totalOccurrences) * (Math.log10(occurrences[i]/totalOccurrences) / Math.log10(2)); 
		}
		return entropy;
	}
}
