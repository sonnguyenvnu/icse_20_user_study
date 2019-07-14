@Override public V valueOfAccessPath(AccessPath path,V defaultValue){
  V result=getInformation(path);
  return result != null ? result : defaultValue;
}
