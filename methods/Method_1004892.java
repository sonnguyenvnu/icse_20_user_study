@Override public void put(final String reference,final Object value){
  if (ELEMENT.equals(reference)) {
    throw new IllegalArgumentException("You are not allowed to set an entire Element on this ElementTuple");
  }
  if (PROPERTIES.equals(reference)) {
    element.copyProperties(((Properties)value));
  }
  final IdentifierType idType=IdentifierType.fromName(reference);
  if (null == idType) {
    element.putProperty(reference,value);
  }
 else {
    element.putIdentifier(idType,value);
  }
}
