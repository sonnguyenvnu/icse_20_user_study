@Override public String serialize(){
  return CONCEPT_PREFIX + ID_DELIM + myConceptId.serialize() + ID_DELIM + getQualifiedName();
}
