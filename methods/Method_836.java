private void populateType(TypeNode node,String className){
  String qualifiedName=className;
  Class<?> myType=PRIMITIVE_TYPES.get(className);
  if (myType == null && importedClasses != null) {
    if (importedClasses.containsKey(className)) {
      qualifiedName=importedClasses.get(className);
    }
 else     if (importedClasses.containsValue(className)) {
      qualifiedName=className;
    }
    if (qualifiedName != null) {
      try {
        myType=pmdClassLoader.loadClass(qualifiedName);
      }
 catch (      ClassNotFoundException e) {
        myType=processOnDemand(qualifiedName);
      }
catch (      NoClassDefFoundError e) {
        myType=processOnDemand(qualifiedName);
      }
catch (      LinkageError e) {
        myType=processOnDemand(qualifiedName);
      }
    }
  }
  if (myType == null && qualifiedName != null && qualifiedName.contains(DOT_STRING)) {
    String qualifiedNameInner=qualifiedName.substring(0,qualifiedName.lastIndexOf(StringAndCharConstants.DOT)) + "$" + qualifiedName.substring(qualifiedName.lastIndexOf(StringAndCharConstants.DOT) + 1);
    try {
      myType=pmdClassLoader.loadClass(qualifiedNameInner);
    }
 catch (    Exception e) {
    }
  }
  if (myType == null && qualifiedName != null && !qualifiedName.contains(DOT_STRING)) {
    try {
      myType=pmdClassLoader.loadClass("java.lang." + qualifiedName);
    }
 catch (    Exception e) {
    }
  }
  if (myType != null) {
    node.setType(myType);
  }
}
