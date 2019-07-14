private long[] checkSortKey(List<PropertyKey> sig){
  for (  PropertyKey key : sig) {
    Preconditions.checkArgument(attributeHandler.isOrderPreservingDatatype(key.dataType()),"Key must have an order-preserving data type to be used as sort key: " + key);
  }
  return checkSignature(sig);
}
