/** 
 * @param q The quantile desired.  Can be in the range [0,1].
 * @return The minimum value x such that we think that the proportion of samples is &le; x is q.
 */
@Override public double quantile(double q){
  if (q < 0 || q > 1) {
    throw new IllegalArgumentException("q should be in [0,1], got " + q);
  }
  AVLGroupTree values=summary;
  if (values.size() == 0) {
    return Double.NaN;
  }
 else   if (values.size() == 1) {
    return values.iterator().next().mean();
  }
  final double index=q * count;
  if (index < 1) {
    return min;
  }
  if (index >= count - 1) {
    return max;
  }
  int currentNode=values.first();
  int currentWeight=values.count(currentNode);
  if (currentWeight == 2 && index <= 2) {
    return 2 * values.mean(currentNode) - min;
  }
  if (values.count(values.last()) == 2 && index > count - 2) {
    return 2 * values.mean(values.last()) - max;
  }
  double weightSoFar=currentWeight / 2.0;
  if (index < weightSoFar) {
    return weightedAverage(min,weightSoFar - index,values.mean(currentNode),index - 1);
  }
  for (int i=0; i < values.size() - 1; i++) {
    int nextNode=values.next(currentNode);
    int nextWeight=values.count(nextNode);
    double dw=(currentWeight + nextWeight) / 2.0;
    if (index < weightSoFar + dw) {
      double leftExclusion=0;
      double rightExclusion=0;
      if (currentWeight == 1) {
        if (index < weightSoFar + 0.5) {
          return values.mean(currentNode);
        }
 else {
          leftExclusion=0.5;
        }
      }
      if (nextWeight == 1) {
        if (index >= weightSoFar + dw - 0.5) {
          return values.mean(nextNode);
        }
 else {
          rightExclusion=0.5;
        }
      }
      assert leftExclusion + rightExclusion < 1;
      assert dw > 1;
      double w1=index - weightSoFar - leftExclusion;
      double w2=weightSoFar + dw - index - rightExclusion;
      return weightedAverage(values.mean(currentNode),w2,values.mean(nextNode),w1);
    }
    weightSoFar+=dw;
    currentNode=nextNode;
    currentWeight=nextWeight;
  }
  assert currentWeight > 1;
  assert index - weightSoFar < currentWeight / 2 - 1;
  assert count - weightSoFar > 0.5;
  double w1=index - weightSoFar;
  double w2=count - 1 - index;
  return weightedAverage(values.mean(currentNode),w2,max,w1);
}
