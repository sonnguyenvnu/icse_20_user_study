@Override public boolean equals(final Object obj){
  if (this == obj) {
    return true;
  }
  if (null == obj || getClass() != obj.getClass()) {
    return false;
  }
  final PropertiesSerialiser serialiser=(PropertiesSerialiser)obj;
  return new EqualsBuilder().append(schema,serialiser.schema).isEquals();
}
