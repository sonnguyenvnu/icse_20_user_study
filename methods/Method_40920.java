@Override public void setFailureThreshold(Ratio threshold){
  bitSet=new CircularBitSet(threshold.getDenominator(),bitSet);
}
