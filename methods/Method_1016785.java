/** 
 * Performs CRF training with label likelihood and entropy regularization.   The CRF is first trained with label likelihood only.  This parameter  setting is used as a starting point for the combined optimization.
 * @param labeled Labeled data, only used for label likelihood term.
 * @param unlabeled Unlabeled data, only used for entropy regularization term.
 * @param numIterations Number of iterations.
 * @return True if training has converged.
 */
public boolean train(InstanceList labeled,InstanceList unlabeled,int numIterations){
  if (iteration == 0) {
    CRFOptimizableByLabelLikelihood likelihood=new CRFOptimizableByLabelLikelihood(crf,labeled);
    likelihood.setGaussianPriorVariance(gaussianPriorVariance);
    this.bfgs=new LimitedMemoryBFGS(likelihood);
    logger.info("CRF about to train with " + numIterations + " iterations");
    for (int i=0; i < numIterations; i++) {
      try {
        converged=bfgs.optimize(1);
        iteration++;
        logger.info("CRF finished one iteration of maximizer, i=" + i);
        runEvaluators();
      }
 catch (      IllegalArgumentException e) {
        e.printStackTrace();
        logger.info("Catching exception; saying converged.");
        converged=true;
      }
catch (      Exception e) {
        e.printStackTrace();
        logger.info("Catching exception; saying converged.");
        converged=true;
      }
      if (converged) {
        logger.info("CRF training has converged, i=" + i);
        break;
      }
    }
    iteration=0;
  }
  CRFOptimizableByLabelLikelihood likelihood=new CRFOptimizableByLabelLikelihood(crf,labeled);
  likelihood.setGaussianPriorVariance(gaussianPriorVariance);
  CRFOptimizableByEntropyRegularization regularization=new CRFOptimizableByEntropyRegularization(crf,unlabeled);
  regularization.setScalingFactor(this.entRegScalingFactor);
  CRFOptimizableByGradientValues regLikelihood=new CRFOptimizableByGradientValues(crf,new Optimizable.ByGradientValue[]{likelihood,regularization});
  this.bfgs=new LimitedMemoryBFGS(regLikelihood);
  converged=false;
  logger.info("CRF about to train with " + numIterations + " iterations");
  for (int reset=0; reset < DEFAULT_NUM_RESETS + 1; reset++) {
    for (int i=0; i < numIterations; i++) {
      try {
        converged=bfgs.optimize(1);
        iteration++;
        logger.info("CRF finished one iteration of maximizer, i=" + i);
        runEvaluators();
      }
 catch (      IllegalArgumentException e) {
        e.printStackTrace();
        logger.info("Catching exception; saying converged.");
        converged=true;
      }
catch (      Exception e) {
        e.printStackTrace();
        logger.info("Catching exception; saying converged.");
        converged=true;
      }
      if (converged) {
        logger.info("CRF training has converged, i=" + i);
        break;
      }
    }
    this.bfgs.reset();
  }
  return converged;
}
