public static ClassMember definedIn3rdPartyClass(PreprocessedSketch ps,ClassMember tehClass,String memberName){
  if (tehClass == null)   return null;
  log("definedIn3rdPartyClass-> Looking for " + memberName + " in " + tehClass);
  String memberNameL=memberName.toLowerCase();
  if (tehClass.getDeclaringNode() instanceof TypeDeclaration) {
    TypeDeclaration td=(TypeDeclaration)tehClass.getDeclaringNode();
    for (int i=0; i < td.getFields().length; i++) {
      List<VariableDeclarationFragment> vdfs=td.getFields()[i].fragments();
      for (      VariableDeclarationFragment vdf : vdfs) {
        if (vdf.getName().toString().toLowerCase().startsWith(memberNameL))         return new ClassMember(ps,vdf);
      }
    }
    for (int i=0; i < td.getMethods().length; i++) {
      if (td.getMethods()[i].getName().toString().toLowerCase().startsWith(memberNameL))       return new ClassMember(ps,td.getMethods()[i]);
    }
    if (td.getSuperclassType() != null) {
      log(getNodeAsString(td.getSuperclassType()) + " <-Looking into superclass of " + tehClass);
      return definedIn3rdPartyClass(ps,new ClassMember(ps,td.getSuperclassType()),memberName);
    }
 else {
      return definedIn3rdPartyClass(ps,new ClassMember(Object.class),memberName);
    }
  }
  Class<?> probableClass;
  if (tehClass.getClass_() != null) {
    probableClass=tehClass.getClass_();
  }
 else {
    probableClass=findClassIfExists(ps,tehClass.getTypeAsString());
    log("Loaded " + probableClass.toString());
  }
  for (  Method method : probableClass.getMethods()) {
    if (method.getName().equalsIgnoreCase(memberName)) {
      return new ClassMember(method);
    }
  }
  for (  Field field : probableClass.getFields()) {
    if (field.getName().equalsIgnoreCase(memberName)) {
      return new ClassMember(field);
    }
  }
  return null;
}
