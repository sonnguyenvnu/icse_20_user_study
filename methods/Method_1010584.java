public static SPropertyId deserialize(String s){
  int split=s.lastIndexOf('/');
  SConceptId concept=SConceptId.deserialize(s.substring(0,split));
  long prop=Long.parseLong(s.substring(split + 1));
  return new SPropertyId(concept,prop);
}
