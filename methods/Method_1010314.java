public static SAbstractConcept deserialize(String s){
  if (s.startsWith(SInterfaceConceptAdapterById.INTERFACE_PREFIX)) {
    return SInterfaceConceptAdapterById.deserialize(s);
  }
 else   if (s.startsWith(SConceptAdapterById.CONCEPT_PREFIX)) {
    return SConceptAdapterById.deserialize(s);
  }
 else   if (s.startsWith(InvalidConcept.INVALID_PREFIX)) {
    return InvalidConcept.deserialize(s);
  }
 else {
    throw new FormatException("Illegal concept type: " + s);
  }
}
