public void add(Type key,Type val){
  keyType=UnionType.union(keyType,key);
  valueType=UnionType.union(valueType,val);
}
