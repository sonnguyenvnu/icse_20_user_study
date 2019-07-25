@Override public boolean equals(final Object obj){
  if (this == obj) {
    return true;
  }
  if (null == obj || getClass() != obj.getClass()) {
    return false;
  }
  final EntityIdSerialiser serialiser=(EntityIdSerialiser)obj;
  return new EqualsBuilder().append(vertexSerialiser,serialiser.vertexSerialiser).isEquals();
}
