@Override public String serialize(){
  return INTERFACE_PREFIX + ID_DELIM + myConceptId.serialize() + ID_DELIM + getQualifiedName();
}
