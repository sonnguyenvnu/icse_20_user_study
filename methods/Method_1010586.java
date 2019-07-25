public static SReferenceLinkId deserialize(String s){
  int split=s.lastIndexOf('/');
  SConceptId concept=SConceptId.deserialize(s.substring(0,split));
  long ref=Long.parseLong(s.substring(split + 1));
  return new SReferenceLinkId(concept,ref);
}
