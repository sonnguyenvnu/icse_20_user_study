/** 
 * Converts comma-separated string into array of Bean references.
 */
private BeanReferences[] convertAnnValueToReferences(String value){
  if (value == null) {
    return null;
  }
  value=value.trim();
  if (value.length() == 0) {
    return null;
  }
  String[] refNames=Converter.get().toStringArray(value);
  BeanReferences[] references=new BeanReferences[refNames.length];
  for (int i=0; i < refNames.length; i++) {
    references[i]=BeanReferences.of(refNames[i].trim());
  }
  return references;
}
