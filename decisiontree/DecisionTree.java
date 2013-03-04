package decisiontree;

import Main.Matrix;
import Main.SupervisedLearner;

public class DecisionTree extends SupervisedLearner {

	private Node _root;
	
	@Override
	public void train(Matrix features, Matrix labels) throws Exception {
		ID3 id3 = new ID3();
		Node _root = id3.buildTree(features, labels);
	}

	@Override
	public void predict(double[] features, double[] labels) throws Exception {
		System.out.println("Predicting...");
//		for (int i = 0; i < features.length; i++) {
//			System.out.println("	Feature" + i + ": " + features[i]);
//		}
	}

}
