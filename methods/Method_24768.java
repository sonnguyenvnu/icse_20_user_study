public static ClassMember definedIn3rdPartyClass(PreprocessedSketch ps,String className,String memberName){
  Class<?> probableClass=findClassIfExists(ps,className);
  if (probableClass == null) {
    log("Couldn't load " + className);
    return null;
  }
  if (memberName.equals("THIS")) {
    return new ClassMember(probableClass);
  }
 else {
    return definedIn3rdPartyClass(ps,new ClassMember(probableClass),memberName);
  }
}
