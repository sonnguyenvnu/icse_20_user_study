public synchronized void addMessage(M message,MessageScope scope,Map<MessageScope,Integer> scopeMap,MessageCombiner<M> combiner){
  assert message != null && scope != null && combiner != null;
  Preconditions.checkArgument(scopeMap.containsKey(scope),"Provided scope was not declared in the VertexProgram: %s",scope);
  assert scopeMap.containsKey(scope);
  initializeCurrentMessages(scopeMap);
  if (scopeMap.size() == 1) {
    if (currentMessages == null)     currentMessages=message;
 else     currentMessages=combiner.combine(message,(M)currentMessages);
  }
 else {
    int pos=scopeMap.get(scope);
    Object[] messages=(Object[])currentMessages;
    if (messages[pos] == null)     messages[pos]=message;
 else     messages[pos]=combiner.combine(message,(M)messages[pos]);
  }
}
