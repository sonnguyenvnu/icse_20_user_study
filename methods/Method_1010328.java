public static SPropertyAdapter deserialize(String s){
  if (s.startsWith(SPropertyAdapterById.PROP_PREFIX)) {
    return SPropertyAdapterById.deserialize(s);
  }
 else   if (s.startsWith(InvalidProperty.INVALID_PREFIX)) {
    return InvalidProperty.deserialize(s);
  }
 else {
    throw new FormatException("Illegal property type: " + s);
  }
}
