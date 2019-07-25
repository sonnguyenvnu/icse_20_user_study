@Override public boolean equals(final Object obj){
  if (this == obj) {
    return true;
  }
  if (null == obj || getClass() != obj.getClass()) {
    return false;
  }
  final MultiSerialiserStorage serialiser=(MultiSerialiserStorage)obj;
  return new EqualsBuilder().append(keyToClass,serialiser.keyToClass).append(keyToSerialiser,serialiser.keyToSerialiser).append(classToKey,serialiser.classToKey).append(consistent,serialiser.consistent).append(preservesObjectOrdering,serialiser.preservesObjectOrdering).isEquals();
}
