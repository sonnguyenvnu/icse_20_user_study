/** 
 * Returns the current value from a computeIfAbsent invocation. 
 */
@Nullable V doComputeIfAbsent(K key,Object keyRef,Function<? super K,? extends V> mappingFunction,long[] now,boolean recordStats){
  @SuppressWarnings("unchecked") V[] oldValue=(V[])new Object[1];
  @SuppressWarnings("unchecked") V[] newValue=(V[])new Object[1];
  @SuppressWarnings("unchecked") K[] nodeKey=(K[])new Object[1];
  @SuppressWarnings({"unchecked","rawtypes"}) Node<K,V>[] removed=new Node[1];
  int[] weight=new int[2];
  RemovalCause[] cause=new RemovalCause[1];
  Node<K,V> node=data.compute(keyRef,(k,n) -> {
    if (n == null) {
      newValue[0]=mappingFunction.apply(key);
      if (newValue[0] == null) {
        return null;
      }
      now[0]=expirationTicker().read();
      weight[1]=weigher.weigh(key,newValue[0]);
      n=nodeFactory.newNode(key,keyReferenceQueue(),newValue[0],valueReferenceQueue(),weight[1],now[0]);
      setVariableTime(n,expireAfterCreate(key,newValue[0],expiry(),now[0]));
      return n;
    }
synchronized (n) {
      nodeKey[0]=n.getKey();
      weight[0]=n.getWeight();
      oldValue[0]=n.getValue();
      if ((nodeKey[0] == null) || (oldValue[0] == null)) {
        cause[0]=RemovalCause.COLLECTED;
      }
 else       if (hasExpired(n,now[0])) {
        cause[0]=RemovalCause.EXPIRED;
      }
 else {
        return n;
      }
      writer.delete(nodeKey[0],oldValue[0],cause[0]);
      newValue[0]=mappingFunction.apply(key);
      if (newValue[0] == null) {
        removed[0]=n;
        n.retire();
        return null;
      }
      weight[1]=weigher.weigh(key,newValue[0]);
      n.setValue(newValue[0],valueReferenceQueue());
      n.setWeight(weight[1]);
      now[0]=expirationTicker().read();
      setVariableTime(n,expireAfterCreate(key,newValue[0],expiry(),now[0]));
      setAccessTime(n,now[0]);
      setWriteTime(n,now[0]);
      return n;
    }
  }
);
  if (node == null) {
    if (removed[0] != null) {
      afterWrite(new RemovalTask(removed[0]));
    }
    return null;
  }
  if (cause[0] != null) {
    if (hasRemovalListener()) {
      notifyRemoval(nodeKey[0],oldValue[0],cause[0]);
    }
    statsCounter().recordEviction(weight[0],cause[0]);
  }
  if (newValue[0] == null) {
    if (!isComputingAsync(node)) {
      tryExpireAfterRead(node,key,oldValue[0],expiry(),now[0]);
      setAccessTime(node,now[0]);
    }
    afterRead(node,now[0],recordStats);
    return oldValue[0];
  }
  if ((oldValue[0] == null) && (cause[0] == null)) {
    afterWrite(new AddTask(node,weight[1]));
  }
 else {
    int weightedDifference=(weight[1] - weight[0]);
    afterWrite(new UpdateTask(node,weightedDifference));
  }
  return newValue[0];
}
