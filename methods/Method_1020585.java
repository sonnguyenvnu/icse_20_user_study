@Override public boolean seek(Request request){
  if (isSequential()) {
    try {
      Integer index=orderedIndex.get();
      RecordedInteraction interaction=interactions.get(index).toImmutable();
      Request nextRequest=interaction == null ? null : interaction.request();
      return nextRequest != null && matchRule.isMatch(request,nextRequest);
    }
 catch (    IndexOutOfBoundsException e) {
      throw new NonWritableTapeException();
    }
  }
 else {
    return findMatch(request) >= 0;
  }
}
