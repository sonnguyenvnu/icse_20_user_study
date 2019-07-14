@Override public void setSuccessThreshold(Ratio threshold){
  bitSet=new CircularBitSet(threshold.getDenominator(),bitSet);
}
