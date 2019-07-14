@Override public Iterator<M> receiveMessages(){
  Stream<M> combinedStream=Stream.empty();
  for (  MessageScope scope : vertexMemory.getPreviousScopes()) {
    combinedStream=Stream.concat(receiveMessages(scope),combinedStream);
  }
  return combinedStream.iterator();
}
