void computeProbabilityEntropy(int length){
  p=frequency / (float)length;
  leftEntropy=computeEntropy(left);
  rightEntropy=computeEntropy(right);
  entropy=Math.min(leftEntropy,rightEntropy);
}
