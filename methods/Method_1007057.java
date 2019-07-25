@Override public FingerTree<V,A> snoc(final A b){
  final MakeTree<V,A> mk=mkTree(measured());
  return mk.deep(mk.one(a),new Empty<>(measured().nodeMeasured()),mk.one(b));
}
