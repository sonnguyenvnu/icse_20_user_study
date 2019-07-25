@Override public Integer apply(Integer t){
  return t < 1000 ? lookup[t] : (int)Math.log10(t);
}
