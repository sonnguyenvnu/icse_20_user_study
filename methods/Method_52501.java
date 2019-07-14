private void populateType(TypeNode node,String className,int arrayDimens){
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
catch (      LinkageError e) {
        if (LOG.isLoggable(Level.FINE)) {
          LOG.log(Level.FINE,"Tried to load class " + qualifiedName + " from on demand import, " + "with an incomplete classpath.",e);
        }
        return;
      }
    }
  }
  if (myType == null && qualifiedName != null && qualifiedName.contains(".")) {
    String qualifiedNameInner=qualifiedName.substring(0,qualifiedName.lastIndexOf('.')) + "$" + qualifiedName.substring(qualifiedName.lastIndexOf('.') + 1);
    try {
      myType=pmdClassLoader.loadClass(qualifiedNameInner);
    }
 catch (    ClassNotFoundException ignored) {
    }
catch (    LinkageError e) {
      if (LOG.isLoggable(Level.FINE)) {
        LOG.log(Level.FINE,"Tried to load class " + qualifiedNameInner + " from on demand import, " + "with an incomplete classpath.",e);
      }
      return;
    }
  }
  if (myType == null && qualifiedName != null && !qualifiedName.contains(".")) {
    try {
      myType=pmdClassLoader.loadClass("java.lang." + qualifiedName);
    }
 catch (    Exception ignored) {
    }
  }
  if (myType == null) {
    ASTTypeParameter parameter=getTypeParameterDeclaration(node,className);
    if (parameter != null) {
      node.setTypeDefinition(parameter.getTypeDefinition());
    }
  }
 else {
    JavaTypeDefinition def=JavaTypeDefinition.forClass(myType);
    if (def != null) {
      node.setTypeDefinition(def.withDimensions(arrayDimens));
    }
  }
}
