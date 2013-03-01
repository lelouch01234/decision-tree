package decisiontree;

import Main.Matrix;
import Main.SupervisedLearner;

public class DecisionTree extends SupervisedLearner {

	@Override
	public void train(Matrix features, Matrix labels) throws Exception {
		ID3 id3 = new ID3();
		id3.findTree(features, labels);
	}

	@Override
	public void predict(double[] features, double[] labels) throws Exception {
		// TODO Auto-generated method stub

	}

}
