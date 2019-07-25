@Override public boolean equals(final Object obj){
  if (this == obj) {
    return true;
  }
  if (null == obj || getClass() != obj.getClass()) {
    return false;
  }
  final SetSerialiser serialiser=(SetSerialiser)obj;
  return new EqualsBuilder().append(objectSerialiser,serialiser.objectSerialiser).append(setClass,serialiser.setClass).isEquals();
}
