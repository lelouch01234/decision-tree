package decisiontree;

import Main.Matrix;
import Main.SupervisedLearner;

public class DecisionTree extends SupervisedLearner {

	private Node _root;
	private Matrix _features;
	private Matrix _labels;
	
	@Override
	public void train(Matrix features, Matrix labels) throws Exception {
		_features = features;
		_labels = labels;
		ID3 id3 = new ID3();
		Node _root = id3.buildTree(features, labels);
		_root.dumpDot(features, labels);
	}

	@Override
	public void predict(double[] features, double[] labels) throws Exception {
//		System.out.println("Predicting...");
//		for (int i = 0; i < features.length; i++) {
//			System.out.println("	Feature" + i + ": " + features[i]);
//		}
		makeDecision(features);
	}
	
	private double makeDecision(double[] features) {
		return 0;
	}
	
	
}
