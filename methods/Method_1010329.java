@Override public String serialize(){
  return PROP_PREFIX + ID_DELIM + myPropertyId.serialize() + ID_DELIM + getName();
}
