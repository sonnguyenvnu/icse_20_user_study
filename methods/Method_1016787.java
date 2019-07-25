/** 
 * Verifies the correctness of the lattice computations.
 */
public void check(ArrayList<GEConstraint> constraints,double[][] gammas,double[][][] xis,FeatureVectorSequence fvs){
  double ex1=0.0;
  for (int ip=0; ip < latticeLength - 1; ++ip) {
    for (int si1=0; si1 < numStates; si1++) {
      for (int si2=0; si2 < numStates; si2++) {
        double dot=0;
        for (        GEConstraint constraint : constraints) {
          dot+=constraint.getCompositeConstraintFeatureValue(fvs.get(ip),ip,si1,si2);
        }
        double prob=Math.exp(xis[ip][si1][si2]);
        ex1+=prob * dot;
      }
    }
  }
  double ex2=0.0;
  for (int ip=0; ip < latticeLength - 1; ++ip) {
    double ex3=0.0;
    for (int s1=0; s1 < numStates; ++s1) {
      LatticeNode node=lattice[ip][s1];
      for (int s2=0; s2 < numStates; ++s2) {
        ex3+=node.alpha[s2].exp() + node.beta[s2].exp();
      }
    }
    assert (ex1 - ex3 < 1e-6) : ex1 + " " + ex3;
    ex2+=ex3;
  }
  ex2=ex2 / (latticeLength - 1);
  assert (ex1 - ex2 < 1e-6) : ex1 + " " + ex2;
}
