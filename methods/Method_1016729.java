public MaxEnt train(InstanceList train,int maxIterations){
  trainingList=train;
  if (constraints == null && constraintsFile != null) {
    HashMap<Integer,double[][]> constraintsMap=FeatureConstraintUtil.readRangeConstraintsFromFile(constraintsFile,trainingList);
    logger.info("number of constraints: " + constraintsMap.size());
    constraints=new ArrayList<MaxEntGEConstraint>();
    MaxEntRangeL2FLGEConstraints geConstraints=new MaxEntRangeL2FLGEConstraints(train.getDataAlphabet().size(),train.getTargetAlphabet().size(),useValues,normalize);
    for (    int fi : constraintsMap.keySet()) {
      double[][] dist=constraintsMap.get(fi);
      for (int li=0; li < dist.length; li++) {
        if (!Double.isInfinite(dist[li][0])) {
          geConstraints.addConstraint(fi,li,dist[li][0],dist[li][1],1);
        }
      }
    }
    constraints.add(geConstraints);
  }
  getOptimizable(trainingList);
  getOptimizer();
  if (opt instanceof LimitedMemoryBFGS) {
    ((LimitedMemoryBFGS)opt).reset();
  }
  logger.fine("trainingList.size() = " + trainingList.size());
  try {
    opt.optimize(maxIterations);
    numIterations+=maxIterations;
  }
 catch (  Exception e) {
    e.printStackTrace();
    logger.info("Catching exception; saying converged.");
  }
  if (maxIterations == Integer.MAX_VALUE && opt instanceof LimitedMemoryBFGS) {
    ((LimitedMemoryBFGS)opt).reset();
    try {
      opt.optimize(maxIterations);
      numIterations+=maxIterations;
    }
 catch (    Exception e) {
      e.printStackTrace();
      logger.info("Catching exception; saying converged.");
    }
  }
  progressLogger.info("\n");
  classifier=ge.getClassifier();
  return classifier;
}
