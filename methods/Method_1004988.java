@Override public boolean equals(final Object obj){
  if (this == obj) {
    return true;
  }
  if (null == obj || getClass() != obj.getClass()) {
    return false;
  }
  final FreqMapSerialiser serialiser=(FreqMapSerialiser)obj;
  return new EqualsBuilder().append(longSerialiser,serialiser.longSerialiser).isEquals();
}
