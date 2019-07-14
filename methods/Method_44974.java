private String getPathAndTypeStr(TypeReference typeRef){
  String name=typeRef.getName();
  String packageStr=typeRef.getPackageName();
  TypeReference mostOuterTypeRef=getMostOuterTypeRef(typeRef);
  String mostOuterTypeName=mostOuterTypeRef.getName();
  if (name != null && packageStr != null && mostOuterTypeName != null && name.trim().length() > 0 && mostOuterTypeName.trim().length() > 0) {
    String pathStr=packageStr.replaceAll("\\.","/") + "/" + mostOuterTypeName;
    String typeStr=packageStr + "." + name.replace(".","$");
    return pathStr + "|" + typeStr;
  }
  return null;
}
