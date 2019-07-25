@Override public boolean equals(final Object obj){
  if (this == obj) {
    return true;
  }
  if (null == obj || getClass() != obj.getClass()) {
    return false;
  }
  final ElementIdSerialiser serialiser=(ElementIdSerialiser)obj;
  return new EqualsBuilder().append(entityIdSerialiser,serialiser.entityIdSerialiser).append(edgeIdSerialiser,serialiser.edgeIdSerialiser).isEquals();
}
