@JsonIgnore public boolean isFiniteRange(){
  return !Double.isInfinite(_min) && !Double.isInfinite(_max);
}
