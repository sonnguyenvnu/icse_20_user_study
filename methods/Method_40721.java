public void add(@NotNull Type key,@NotNull Type val){
  keyType=UnionType.union(keyType,key);
  valueType=UnionType.union(valueType,val);
}
