@Override public boolean equals(final Object obj){
  if (this == obj) {
    return true;
  }
  if (null == obj || getClass() != obj.getClass()) {
    return false;
  }
  final ElementSerialiser serialiser=(ElementSerialiser)obj;
  return new EqualsBuilder().append(entitySerialiser,serialiser.entitySerialiser).append(edgeSerialiser,serialiser.edgeSerialiser).isEquals();
}
