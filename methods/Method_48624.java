/** 
 * Sets time-to-live for those schema types that support it
 * @param type
 * @param duration Note that only 'seconds' granularity is supported
 */
@Override public void setTTL(final JanusGraphSchemaType type,final Duration duration){
  if (!graph.getBackend().getStoreFeatures().hasCellTTL())   throw new UnsupportedOperationException("The storage engine does not support TTL");
  if (type instanceof VertexLabelVertex) {
    Preconditions.checkArgument(((VertexLabelVertex)type).isStatic(),"must define vertex label as static to allow setting TTL");
  }
 else {
    Preconditions.checkArgument(type instanceof EdgeLabelVertex || type instanceof PropertyKeyVertex,"TTL is not supported for type " + type.getClass().getSimpleName());
  }
  Preconditions.checkArgument(type instanceof JanusGraphSchemaVertex);
  Integer ttlSeconds=(duration.isZero()) ? null : (int)duration.getSeconds();
  setTypeModifier(type,ModifierType.TTL,ttlSeconds);
}
