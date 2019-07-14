@Override public String getLinkDescription(String uniqueStr){
  String readableLink=null;
  try {
    if (uniqueStr == null)     return null;
    String[] linkParts=uniqueStr.split("\\|");
    if (linkParts.length < 3)     return null;
    String typeStr=linkParts[2];
    TypeReference typeRef=metadataSystem.lookupType(typeStr.replaceAll("\\.","/"));
    if (typeRef == null)     return null;
    TypeDefinition typeDef=typeRef.resolve();
    if (typeDef == null)     return null;
    String declaredSuffix="";
    String mostOuterTypeStr=linkParts[1].replaceAll("/",".");
    boolean isOwnFile=mostOuterTypeStr.equals(currentTypeQualifiedName);
    if (!isOwnFile) {
      declaredSuffix=" - Declared: " + mostOuterTypeStr;
    }
    if (uniqueStr.startsWith("type")) {
      String desc=typeDef.getBriefDescription();
      if (desc != null && desc.trim().length() > 0) {
        readableLink=desc;
      }
    }
 else     if (uniqueStr.startsWith("method")) {
      MethodDefinition methodDef=findMethodInType(typeDef,uniqueStr);
      if (methodDef == null)       return null;
      String desc=methodDef.getBriefDescription();
      if (desc != null && desc.trim().length() > 0) {
        if (desc.contains("void <init>")) {
          String constructorName=typeDef.getName();
          TypeReference declaringTypeRef=typeRef.getDeclaringType();
          if (declaringTypeRef != null) {
            TypeDefinition declaringTypeDef=declaringTypeRef.resolve();
            if (declaringTypeDef != null) {
              String declaringTypeName=declaringTypeDef.getName();
              if (declaringTypeName != null) {
                constructorName=StringUtilities.removeLeft(constructorName,declaringTypeName);
                constructorName=constructorName.replaceAll("^(\\.|\\$)","");
              }
            }
          }
          desc=desc.replace("void <init>",constructorName);
          readableLink="Constructor: " + erasePackageInfoFromDesc(desc) + declaredSuffix;
        }
 else {
          readableLink=erasePackageInfoFromDesc(desc) + declaredSuffix;
        }
      }
    }
 else     if (uniqueStr.startsWith("field")) {
      FieldDefinition fieldDef=findFieldInType(typeDef,uniqueStr);
      if (fieldDef == null)       return null;
      String desc=fieldDef.getBriefDescription();
      if (desc != null && desc.trim().length() > 0) {
        readableLink=erasePackageInfoFromDesc(desc) + declaredSuffix;
      }
    }
    if (readableLink != null) {
      readableLink=readableLink.replace("$",".");
    }
  }
 catch (  Exception e) {
    readableLink=null;
    Luyten.showExceptionDialog("Exception!",e);
  }
  return readableLink;
}
