private void convertCallToNew(Ref ref,List<Binding> bindings){
  if (ref.isRef()) {
    return;
  }
  if (bindings.isEmpty()) {
    return;
  }
  Binding nb=bindings.get(0);
  Type t=nb.getType();
  if (t.isUnionType()) {
    t=t.asUnionType().firstUseful();
    if (t == null) {
      return;
    }
  }
  if (!t.isUnknownType() && !t.isFuncType()) {
    ref.markAsNew();
  }
}
