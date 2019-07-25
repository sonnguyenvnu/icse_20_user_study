@Override public boolean equals(final Object obj){
  if (this == obj) {
    return true;
  }
  if (null == obj || getClass() != obj.getClass()) {
    return false;
  }
  final ReservoirItemsSketchSerialiser serialiser=(ReservoirItemsSketchSerialiser)obj;
  return new EqualsBuilder().append(arrayOfItemsSerDe,serialiser.arrayOfItemsSerDe).isEquals();
}
