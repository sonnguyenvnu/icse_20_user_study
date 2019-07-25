private void configure(NullAllowed nullAllowed){
  if (nullAllowed.probability() >= 0.0f && nullAllowed.probability() <= 1.0f) {
    this.probabilityOfNull=nullAllowed.probability();
  }
 else {
    throw new IllegalArgumentException("NullAllowed probability must be in the [0,1] range");
  }
}
