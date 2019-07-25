public SourceReference pop(){
  if (propertyEntries.size() > 1) {
    List<PropertyEntry> newPropertyEntries=new ArrayList<>(propertyEntries.subList(1,propertyEntries.size()));
    return new SourceReference(parameter,newPropertyEntries,isValid);
  }
 else {
    return null;
  }
}
