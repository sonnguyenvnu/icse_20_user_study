/** 
 * Computes the Jaccard similarity ratio with upper and lower bounds. The Jaccard similarity ratio <i>J(A,B) = (A ^ B)/(A U B)</i> is used to measure how similar the two sketches are to each other. If J = 1.0, the sketches are considered equal. If J = 0, the two sketches are distinct from each other. A Jaccard of .95 means the overlap between the two populations is 95% of the union of the two populations. <p>Note: For very large pairs of sketches, where the configured nominal entries of the sketches are 2^25 or 2^26, this method may produce unpredictable results.
 * @param sketchA given sketch A
 * @param sketchB given sketch B
 * @return a double array {LowerBound, Estimate, UpperBound} of the Jaccard ratio.The Upper and Lower bounds are for a confidence interval of 95.4% or +/- 2 standard deviations.
 */
public static double[] jaccard(final Sketch sketchA,final Sketch sketchB){
  if ((sketchA == null) || (sketchB == null)) {
    return ZEROS.clone();
  }
  if (sketchA == sketchB) {
    return ONES.clone();
  }
  if (sketchA.isEmpty() && sketchB.isEmpty()) {
    return ONES.clone();
  }
  if (sketchA.isEmpty() || sketchB.isEmpty()) {
    return ZEROS.clone();
  }
  final int countA=sketchA.getRetainedEntries();
  final int countB=sketchB.getRetainedEntries();
  final int minK=1 << MIN_LG_NOM_LONGS;
  final int maxK=1 << MAX_LG_NOM_LONGS;
  final int newK=max(min(ceilingPowerOf2(countA + countB),maxK),minK);
  final Union union=SetOperation.builder().setNominalEntries(newK).buildUnion();
  union.update(sketchA);
  union.update(sketchB);
  final Sketch unionAB=union.getResult();
  final long thetaLongUAB=unionAB.getThetaLong();
  final long thetaLongA=sketchA.getThetaLong();
  final long thetaLongB=sketchB.getThetaLong();
  final int countUAB=unionAB.getRetainedEntries();
  if ((countUAB == countA) && (countUAB == countB) && (thetaLongUAB == thetaLongA) && (thetaLongUAB == thetaLongB)) {
    return ONES.clone();
  }
  final Intersection inter=SetOperation.builder().buildIntersection();
  inter.update(sketchA);
  inter.update(sketchB);
  inter.update(unionAB);
  final Sketch interABU=inter.getResult(true,null);
  final double lb=getLowerBoundForBoverA(unionAB,interABU);
  final double est=getEstimateOfBoverA(unionAB,interABU);
  final double ub=getUpperBoundForBoverA(unionAB,interABU);
  return new double[]{lb,est,ub};
}
