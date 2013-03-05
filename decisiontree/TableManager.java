package decisiontree;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import Main.Matrix;

public class TableManager {

	private Matrix _examples;
	private Matrix _targetAttributes;
	private LinkedHashSet<Attribute> _attributes;
	
	public TableManager (Matrix examples, Matrix targetAttributes, LinkedHashSet<Attribute> attributes) {
		_examples = examples;
		_targetAttributes = targetAttributes;
		_attributes = attributes;
	}
	
	public LinkedHashSet<Attribute> get_attributes() {
		return _attributes;
	}

	public void set_attributes(LinkedHashSet<Attribute> attributes) {
		_attributes = attributes;
	}

	public Matrix get_examples() {
		return _examples;
	}

	public void set_examples(Matrix examples) {
		_examples = examples;
	}

	public Matrix get_targetAttributes() {
		return _targetAttributes;
	}

	public void set_targetAttributes(Matrix targetAttributes) {
		_targetAttributes = targetAttributes;
	}

	public int[] getTargetAttributeOccurrences(Matrix targetAttributes) {
		int cwise = _targetAttributes.valueCount(0);
		int[] classificationCount = new int[cwise];
		for (int i = 0; i < cwise; i++) {
			classificationCount[i] = 0;
			for (int j = 0; j < targetAttributes.rows(); j++) {
				if ((double) i == targetAttributes.get(j, 0))
					classificationCount[i]++;
			}
		}
		return classificationCount;
	}
	
	public ArrayList<int[]> getValueOccurrences (int attribute, Matrix examples, Matrix targetAttributes) {
		int cwise = _targetAttributes.valueCount(0);
		int valuewise = _examples.valueCount(attribute);
		ArrayList<int[]> valueOccurrences = new ArrayList<int[]>();
		for (int i = 0; i < valuewise; i++) {
			int[] valueOccurrence = new int[cwise];
			for (int j = 0; j < examples.rows(); j++) {
				int targetValue = (int)targetAttributes.get(j, 0);
				if ( i == (int)(examples.get(j, attribute)) )
					valueOccurrence[targetValue]++;
			}
			valueOccurrences.add(valueOccurrence);
		}
		return valueOccurrences;
	}
	
	public Matrix[] getTrimmedMatrices(int attribute, int value) {
		/*Matrix[] trimmedMatrices = new Matrix[2];
		Matrix trimmedExamples = new Matrix();
		Matrix trimmedTargetAttributes = new Matrix();
		ArrayList<double[]> exampleRowsToCopy = new ArrayList<double[]>();
		ArrayList<double[]> targetRowsToCopy = new ArrayList<double[]>();
		for (int i = 0; i < _examples.rows(); i++) {
			if (_examples.get(i, attribute) == value) {
				exampleRowsToCopy.add(_examples.row(i));
				targetRowsToCopy.add(_targetAttributes.row(i));
			}
		}
		trimmedExamples.setSize(exampleRowsToCopy.size(), _examples.cols());
		trimmedTargetAttributes.setSize(targetRowsToCopy.size(), 1);
		for (int i = 0; i < exampleRowsToCopy.size(); i++) {
			for (int j = 0; j < exampleRowsToCopy.get(i).length; j++) {
				trimmedExamples.set(i, j, exampleRowsToCopy.get(i)[j]);
			}
		}
		for (int i = 0; i < targetRowsToCopy.size(); i++) {
			trimmedTargetAttributes.set(i, 0, targetRowsToCopy.get(i)[0]);
		}
		trimmedMatrices[0] = trimmedExamples;
		trimmedMatrices[1] = trimmedTargetAttributes;
		return trimmedMatrices;*/
		Matrix trimmedExamples = new Matrix(_examples, attribute, value, _examples.rows(), _examples.cols(), new ArrayList<Integer>());
		ArrayList<Integer> whichToCopy = trimmedExamples.getWhichRowsToCopy();
		Matrix trimmedTargetAttributes = new Matrix(_targetAttributes, 0, -1, _targetAttributes.rows(), _targetAttributes.cols(), whichToCopy);
		Matrix[] trimmedMatrices = new Matrix[2];
		trimmedMatrices[0] = trimmedExamples;
		trimmedMatrices[1] = trimmedTargetAttributes;
		return trimmedMatrices;
	}
}
