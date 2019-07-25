/** 
 * Runs the simulated annealing algorithm and produces a model with new coefficients that, theoretically fit the data better and generalizes to future forecasts without overfitting.
 * @param model         The MovAvgModel to be optimized for
 * @param train         A training set provided to the model, which predictions will begenerated from
 * @param test          A test set of data to compare the predictions against and derivea cost for the model
 * @return              A new, minimized model that (theoretically) better fits the data
 */
public static MovAvgModel minimize(MovAvgModel model,EvictingQueue<Double> train,double[] test){
  double temp=1;
  double minTemp=0.0001;
  int iterations=100;
  double alpha=0.9;
  MovAvgModel bestModel=model;
  MovAvgModel oldModel=model;
  double oldCost=cost(model,train,test);
  double bestCost=oldCost;
  while (temp > minTemp) {
    for (int i=0; i < iterations; i++) {
      MovAvgModel newModel=oldModel.neighboringModel();
      double newCost=cost(newModel,train,test);
      double ap=acceptanceProbability(oldCost,newCost,temp);
      if (ap > Math.random()) {
        oldModel=newModel;
        oldCost=newCost;
        if (newCost < bestCost) {
          bestCost=newCost;
          bestModel=newModel;
        }
      }
    }
    temp*=alpha;
  }
  return bestModel;
}
