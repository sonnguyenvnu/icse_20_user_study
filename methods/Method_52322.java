private boolean isAllowedResourceType(TypeNode refType){
  List<String> allowedResourceTypes=getProperty(ALLOWED_RESOURCE_TYPES);
  if (refType.getType() != null && allowedResourceTypes != null) {
    for (    String type : allowedResourceTypes) {
      if (TypeHelper.isExactlyA(refType,type)) {
        return true;
      }
    }
  }
  return false;
}
