private static <V>OrderPreservingSerializer<V> ensureOrderPreserving(AttributeSerializer<V> serializer,Class<V> type){
  Preconditions.checkArgument(serializer instanceof OrderPreservingSerializer,"Registered serializer for datatype does not support order: %s",type);
  return (OrderPreservingSerializer)serializer;
}
