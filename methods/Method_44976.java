private MethodDefinition findMethodInType(TypeDefinition typeDef,String uniqueStr){
  String[] linkParts=uniqueStr.split("\\|");
  if (linkParts.length != 5)   return null;
  String methodName=linkParts[3];
  String methodErasedSignature=linkParts[4];
  if (methodName.trim().length() <= 0 || methodErasedSignature.trim().length() <= 0)   return null;
  List<MethodDefinition> declaredMethods=typeDef.getDeclaredMethods();
  if (declaredMethods == null)   return null;
  boolean isFound=false;
  for (  MethodDefinition declaredMethod : declaredMethods) {
    isFound=(declaredMethod != null && methodName.equals(declaredMethod.getName()));
    isFound=(isFound && methodErasedSignature.equals(declaredMethod.getErasedSignature()));
    if (isFound) {
      if (declaredMethod.isSynthetic() && !settings.getShowSyntheticMembers()) {
        return null;
      }
 else {
        return declaredMethod;
      }
    }
  }
  return null;
}
