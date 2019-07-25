@Override public boolean equals(final Object obj){
  if (this == obj) {
    return true;
  }
  if (null == obj || getClass() != obj.getClass()) {
    return false;
  }
  final EdgeIdSerialiser serialiser=(EdgeIdSerialiser)obj;
  return new EqualsBuilder().append(booleanSerialiser,serialiser.booleanSerialiser).append(vertexSerialiser,serialiser.vertexSerialiser).isEquals();
}
