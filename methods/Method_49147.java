private void verifyWriteAccess(JanusGraphVertex... vertices){
  if (config.isReadOnly())   throw new ReadOnlyTransactionException("Cannot create new entities in read-only transaction");
  for (  JanusGraphVertex v : vertices) {
    if (v.hasId() && idInspector.isUnmodifiableVertex(v.longId()) && !v.isNew())     throw new SchemaViolationException("Cannot modify unmodifiable vertex: " + v);
  }
  verifyAccess(vertices);
}
