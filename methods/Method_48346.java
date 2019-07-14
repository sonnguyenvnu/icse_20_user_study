/** 
 * Signals the transaction that it has been used in a call to {@link ExpectedValueCheckingStore#mutate(StaticBuffer,List,List,StoreTransaction)}. This transaction can't be used in subsequent calls to {@link ExpectedValueCheckingStore#acquireLock(StaticBuffer,StaticBuffer,StaticBuffer,StoreTransaction)}. <p> Calling this method at the appropriate time is handled automatically by {@link ExpectedValueCheckingStore}. JanusGraph users don't need to call this method by hand.
 */
private void mutationStarted(){
  isMutationStarted=true;
}
