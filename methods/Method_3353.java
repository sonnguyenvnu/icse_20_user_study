/** 
 * ??
 * @param context ??
 * @param prior ????
 * @param model ????
 * @return
 */
public static double[] eval(int[] context,double[] prior,EvalParameters model){
  Context[] params=model.getParams();
  int numfeats[]=new int[model.getNumOutcomes()];
  int[] activeOutcomes;
  double[] activeParameters;
  double value=1;
  for (int ci=0; ci < context.length; ci++) {
    if (context[ci] >= 0) {
      Context predParams=params[context[ci]];
      activeOutcomes=predParams.getOutcomes();
      activeParameters=predParams.getParameters();
      for (int ai=0; ai < activeOutcomes.length; ai++) {
        int oid=activeOutcomes[ai];
        numfeats[oid]++;
        prior[oid]+=activeParameters[ai] * value;
      }
    }
  }
  double normal=0.0;
  for (int oid=0; oid < model.getNumOutcomes(); oid++) {
    if (model.getCorrectionParam() != 0) {
      prior[oid]=Math.exp(prior[oid] * model.getConstantInverse() + ((1.0 - ((double)numfeats[oid] / model.getCorrectionConstant())) * model.getCorrectionParam()));
    }
 else {
      prior[oid]=Math.exp(prior[oid] * model.getConstantInverse());
    }
    normal+=prior[oid];
  }
  for (int oid=0; oid < model.getNumOutcomes(); oid++) {
    prior[oid]/=normal;
  }
  return prior;
}
