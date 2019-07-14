@NotNull public TupleType toTupleType(int n){
  TupleType ret=new TupleType();
  for (int i=0; i < n; i++) {
    ret.add(keyType);
  }
  return ret;
}
