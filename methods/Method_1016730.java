public MaxEnt train(InstanceList train,int maxIterations){
  trainingList=train;
  if (constraints == null && constraintsFile != null) {
    HashMap<Integer,double[]> constraintsMap=FeatureConstraintUtil.readConstraintsFromFile(constraintsFile,trainingList);
    logger.info("number of constraints: " + constraintsMap.size());
    constraints=new ArrayList<MaxEntGEConstraint>();
    if (l2) {
      MaxEntL2FLGEConstraints geConstraints=new MaxEntL2FLGEConstraints(train.getDataAlphabet().size(),train.getTargetAlphabet().size(),useValues,normalize);
      for (      int fi : constraintsMap.keySet()) {
        geConstraints.addConstraint(fi,constraintsMap.get(fi),1);
      }
      constraints.add(geConstraints);
    }
 else {
      MaxEntKLFLGEConstraints geConstraints=new MaxEntKLFLGEConstraints(train.getDataAlphabet().size(),train.getTargetAlphabet().size(),useValues);
      for (      int fi : constraintsMap.keySet()) {
        geConstraints.addConstraint(fi,constraintsMap.get(fi),1);
      }
      constraints=new ArrayList<MaxEntGEConstraint>();
      constraints.add(geConstraints);
    }
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
