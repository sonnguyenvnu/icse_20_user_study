public MaxEnt train(InstanceList data,int minIterations,int maxIterations){
  if (constraints == null && constraintsFile != null) {
    HashMap<Integer,double[]> constraintsMap=FeatureConstraintUtil.readConstraintsFromFile(constraintsFile,data);
    logger.info("number of constraints: " + constraintsMap.size());
    constraints=new ArrayList<MaxEntPRConstraint>();
    MaxEntL2FLPRConstraints prConstraints=new MaxEntL2FLPRConstraints(data.getDataAlphabet().size(),data.getTargetAlphabet().size(),useValues,normalize);
    for (    int fi : constraintsMap.keySet()) {
      prConstraints.addConstraint(fi,constraintsMap.get(fi),qGPV);
    }
    constraints.add(prConstraints);
  }
  BitSet instancesWithConstraints=new BitSet(data.size());
  for (  MaxEntPRConstraint constraint : constraints) {
    BitSet bitset=constraint.preProcess(data);
    instancesWithConstraints.or(bitset);
  }
  InstanceList unlabeled=data.cloneEmpty();
  for (int ii=0; ii < data.size(); ii++) {
    if (instancesWithConstraints.get(ii)) {
      boolean noLabel=data.get(ii).getTarget() == null;
      if (noLabel) {
        data.get(ii).unLock();
        data.get(ii).setTarget(new NullLabel((LabelAlphabet)data.getTargetAlphabet()));
      }
      unlabeled.add(data.get(ii));
    }
  }
  int numFeatures=unlabeled.getDataAlphabet().size();
  int numParameters=(numFeatures + 1) * unlabeled.getTargetAlphabet().size();
  if (p == null) {
    p=new MaxEnt(unlabeled.getPipe(),new double[numParameters]);
  }
  q=new PRAuxClassifier(unlabeled.getPipe(),constraints);
  double oldValue=-Double.MAX_VALUE;
  for (numIterations=0; numIterations < maxIterations; numIterations++) {
    double[][] base=optimizeQ(unlabeled,p,numIterations == 0);
    double value=optimizePAndComputeValue(unlabeled,q,base,pGPV);
    logger.info("iteration " + numIterations + " total value " + value);
    if (numIterations >= (minIterations - 1) && 2.0 * Math.abs(value - oldValue) <= tolerance * (Math.abs(value) + Math.abs(oldValue) + 1e-5)) {
      logger.info("PR value difference below tolerance (oldValue: " + oldValue + " newValue: " + value + ")");
      converged=true;
      break;
    }
    oldValue=value;
  }
  return p;
}
