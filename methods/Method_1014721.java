private V initialize(InternalThreadLocalMap threadLocalMap){
  V v=null;
  try {
    v=initialValue();
  }
 catch (  Exception e) {
    throw new RuntimeException(e);
  }
  threadLocalMap.setIndexedVariable(index,v);
  addToVariablesToRemove(threadLocalMap,this);
  return v;
}
