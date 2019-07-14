private String getLinkDescriptionForOffset(int offset){
  String uniqueStr=getUniqueStrForOffset(offset);
  if (uniqueStr != null) {
    String description=this.getLinkDescription(uniqueStr);
    if (description != null) {
      return description;
    }
  }
  return null;
}
