@Override public boolean equals(final Object obj){
  if (this == obj) {
    return true;
  }
  if (null == obj || getClass() != obj.getClass()) {
    return false;
  }
  final MapSerialiser serialiser=(MapSerialiser)obj;
  return new EqualsBuilder().append(keySerialiser,serialiser.keySerialiser).append(valueSerialiser,serialiser.valueSerialiser).append(mapClass,serialiser.mapClass).isEquals();
}
