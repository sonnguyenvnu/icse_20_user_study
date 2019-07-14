/** 
 * Attempts to compute a mapping for the specified key and its current mapped value (or {@code null} if there is no current mapping).<p> An entry that has expired or been reference collected is evicted and the computation continues as if the entry had not been present. This method does not pre-screen and does not wrap the remappingFunction to be statistics aware.
 * @param key key with which the specified value is to be associated
 * @param keyRef the key to associate with or a lookup only key if not <tt>computeIfAbsent</tt>
 * @param remappingFunction the function to compute a value
 * @param now the current time, according to the ticker
 * @param computeIfAbsent if an absent entry can be computed
 * @return the new value associated with the specified key, or null if none
 */
@SuppressWarnings("PMD.EmptyIfStmt") @Nullable V remap(K key,Object keyRef,BiFunction<? super K,? super V,? extends V> remappingFunction,long[] now,boolean computeIfAbsent){
  @SuppressWarnings("unchecked") K[] nodeKey=(K[])new Object[1];
  @SuppressWarnings("unchecked") V[] oldValue=(V[])new Object[1];
  @SuppressWarnings("unchecked") V[] newValue=(V[])new Object[1];
  @SuppressWarnings({"unchecked","rawtypes"}) Node<K,V>[] removed=new Node[1];
  int[] weight=new int[2];
  RemovalCause[] cause=new RemovalCause[1];
  Node<K,V> node=data.compute(keyRef,(kr,n) -> {
    if (n == null) {
      if (!computeIfAbsent) {
        return null;
      }
      newValue[0]=remappingFunction.apply(key,null);
      if (newValue[0] == null) {
        return null;
      }
      now[0]=expirationTicker().read();
      weight[1]=weigher.weigh(key,newValue[0]);
      n=nodeFactory.newNode(keyRef,newValue[0],valueReferenceQueue(),weight[1],now[0]);
      setVariableTime(n,expireAfterCreate(key,newValue[0],expiry(),now[0]));
      return n;
    }
synchronized (n) {
      nodeKey[0]=n.getKey();
      oldValue[0]=n.getValue();
      if ((nodeKey[0] == null) || (oldValue[0] == null)) {
        cause[0]=RemovalCause.COLLECTED;
      }
 else       if (hasExpired(n,now[0])) {
        cause[0]=RemovalCause.EXPIRED;
      }
      if (cause[0] != null) {
        writer.delete(nodeKey[0],oldValue[0],cause[0]);
        if (!computeIfAbsent) {
          removed[0]=n;
          n.retire();
          return null;
        }
      }
      newValue[0]=remappingFunction.apply(nodeKey[0],(cause[0] == null) ? oldValue[0] : null);
      if (newValue[0] == null) {
        if (cause[0] == null) {
          cause[0]=RemovalCause.EXPLICIT;
        }
        removed[0]=n;
        n.retire();
        return null;
      }
      weight[0]=n.getWeight();
      weight[1]=weigher.weigh(key,newValue[0]);
      now[0]=expirationTicker().read();
      if (cause[0] == null) {
        if (newValue[0] != oldValue[0]) {
          cause[0]=RemovalCause.REPLACED;
        }
        setVariableTime(n,expireAfterUpdate(n,key,newValue[0],expiry(),now[0]));
      }
 else {
        setVariableTime(n,expireAfterCreate(key,newValue[0],expiry(),now[0]));
      }
      n.setValue(newValue[0],valueReferenceQueue());
      n.setWeight(weight[1]);
      setAccessTime(n,now[0]);
      setWriteTime(n,now[0]);
      return n;
    }
  }
);
  if (cause[0] != null) {
    if (cause[0].wasEvicted()) {
      statsCounter().recordEviction(weight[0],cause[0]);
    }
    if (hasRemovalListener()) {
      notifyRemoval(nodeKey[0],oldValue[0],cause[0]);
    }
  }
  if (removed[0] != null) {
    afterWrite(new RemovalTask(removed[0]));
  }
 else   if (node == null) {
  }
 else   if ((oldValue[0] == null) && (cause[0] == null)) {
    afterWrite(new AddTask(node,weight[1]));
  }
 else {
    int weightedDifference=weight[1] - weight[0];
    if (expiresAfterWrite() || (weightedDifference != 0)) {
      afterWrite(new UpdateTask(node,weightedDifference));
    }
 else {
      if (cause[0] == null) {
        if (!isComputingAsync(node)) {
          tryExpireAfterRead(node,key,newValue[0],expiry(),now[0]);
          setAccessTime(node,now[0]);
        }
      }
 else       if (cause[0] == RemovalCause.COLLECTED) {
        scheduleDrainBuffers();
      }
      afterRead(node,now[0],false);
    }
  }
  return newValue[0];
}
