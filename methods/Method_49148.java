public final void verifyAccess(JanusGraphVertex... vertices){
  verifyOpen();
  for (  JanusGraphVertex v : vertices) {
    Preconditions.checkArgument(v instanceof InternalVertex,"Invalid vertex: %s",v);
    if (!(v instanceof SystemRelationType) && this != ((InternalVertex)v).tx())     throw new IllegalStateException("The vertex or type is not associated with this transaction [" + v + "]");
    if (v.isRemoved())     throw new IllegalStateException("The vertex or type has been removed [" + v + "]");
  }
}
