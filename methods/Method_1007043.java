@Override public FingerTree<V,A> tail(){
  return deepL(measured(),prefix.tail(),middle,suffix);
}
