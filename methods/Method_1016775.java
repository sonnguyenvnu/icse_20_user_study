public boolean train(InstanceList trainingSet,InstanceList unlabeledSet,int numIterations){
  if (hmm.emissionEstimator == null)   hmm.reset();
  converged=false;
  double threshold=0.001;
  double logLikelihood=Double.NEGATIVE_INFINITY, prevLogLikelihood;
  for (int iter=0; iter < numIterations; iter++) {
    prevLogLikelihood=logLikelihood;
    logLikelihood=0;
    for (    Instance inst : trainingSet) {
      FeatureSequence input=(FeatureSequence)inst.getData();
      FeatureSequence output=(FeatureSequence)inst.getTarget();
      double obsLikelihood=new SumLatticeDefault(hmm,input,output,hmm.new Incrementor()).getTotalWeight();
      logLikelihood+=obsLikelihood;
    }
    logger.info("getValue() (observed log-likelihood) = " + logLikelihood);
    if (unlabeledSet != null) {
      int numEx=0;
      for (      Instance inst : unlabeledSet) {
        numEx++;
        if (numEx % 100 == 0) {
          System.err.print(numEx + ". ");
          System.err.flush();
        }
        FeatureSequence input=(FeatureSequence)inst.getData();
        double hiddenLikelihood=new SumLatticeDefault(hmm,input,null,hmm.new Incrementor()).getTotalWeight();
        logLikelihood+=hiddenLikelihood;
      }
      System.err.println();
    }
    logger.info("getValue() (log-likelihood) = " + logLikelihood);
    hmm.estimate();
    iterationCount++;
    logger.info("HMM finished one iteration of maximizer, i=" + iter);
    runEvaluators();
    if (Math.abs(logLikelihood - prevLogLikelihood) < threshold) {
      converged=true;
      logger.info("HMM training has converged, i=" + iter);
      break;
    }
  }
  return converged;
}
