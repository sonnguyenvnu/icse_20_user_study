public boolean optimize(int numIterations){
  int iterations;
  double fret;
  double fp=optimizable.getValue();
  double[] xi=new double[optimizable.getNumParameters()];
  optimizable.getValueGradient(xi);
  for (iterations=0; iterations < numIterations; iterations++) {
    logger.info("At iteration " + iterations + ", cost = " + fp + ", scaled = " + maxStep + " step = " + step + ", gradient infty-norm = " + MatrixOps.infinityNorm(xi));
    double sum=MatrixOps.twoNorm(xi);
    if (sum > stpmax) {
      logger.info("*** Step 2-norm " + sum + " greater than max " + stpmax + "  Scaling...");
      MatrixOps.timesEquals(xi,stpmax / sum);
    }
    step=lineMaximizer.optimize(xi,step);
    fret=optimizable.getValue();
    if (2.0 * Math.abs(fret - fp) <= tolerance * (Math.abs(fret) + Math.abs(fp) + eps)) {
      logger.info("Gradient Ascent: Value difference " + Math.abs(fret - fp) + " below " + "tolerance; saying converged.");
      converged=true;
      return true;
    }
    fp=fret;
    optimizable.getValueGradient(xi);
    if (eval != null) {
      eval.evaluate(optimizable,iterations);
    }
  }
  return false;
}
