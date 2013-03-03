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

	public void set_attributes(LinkedHashSet<Attribute> _attributes) {
		this._attributes = _attributes;
	}

	public Matrix get_examples() {
		return _examples;
	}

	public void set_examples(Matrix _examples) {
		this._examples = _examples;
	}

	public Matrix get_targetAttributes() {
		return _targetAttributes;
	}

	public void set_targetAttributes(Matrix _targetAttributes) {
		this._targetAttributes = _targetAttributes;
	}

	public int[] getTargetAttributeOccurrences() {
		int cwise = _targetAttributes.valueCount(0);
		int[] classificationCount = new int[cwise];
		for (int i = 0; i < cwise; i++) {
			classificationCount[i] = 0;
			for (int j = 0; j < _targetAttributes.rows(); j++) {
				if ((double) i == _targetAttributes.get(j, 0))
					classificationCount[i]++;
			}
		}
		return classificationCount;
	}
	
	public ArrayList<int[]> getValueOccurrences (int attribute) {
		int cwise = _targetAttributes.valueCount(0);	// length of each int array
		int valuewise = _examples.valueCount(attribute);
		ArrayList<int[]> valueOccurrences = new ArrayList<int[]>();
		for (int i = 0; i < valuewise; i++) {
			int[] valueOccurrence = new int[cwise];
			for (int j = 0; j < _examples.rows(); j++) {
				int targetValue = (int)_targetAttributes.get(j, 0);
				if ( i == (int)(_examples.get(j, attribute)) )
					valueOccurrence[targetValue]++; 
			}
			valueOccurrences.add(valueOccurrence);
		}
		return valueOccurrences;
	}
	
	public Matrix[] getTrimmedMatrices(int attribute, int value) {
		Matrix[] trimmedMatrices = new Matrix[2];
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
		return trimmedMatrices;
	}
}
