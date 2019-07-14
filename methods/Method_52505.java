/** 
 * If the outer class wasn't found then we'll get in here
 * @param node
 */
private void populateImports(ASTCompilationUnit node){
  List<ASTImportDeclaration> theImportDeclarations=node.findChildrenOfType(ASTImportDeclaration.class);
  importedClasses.putAll(JAVA_LANG);
  for (  ASTImportDeclaration anImportDeclaration : theImportDeclarations) {
    String strPackage=anImportDeclaration.getPackageName();
    if (anImportDeclaration.isStatic()) {
      if (anImportDeclaration.isImportOnDemand()) {
        importOnDemandStaticClasses.add(JavaTypeDefinition.forClass(loadClass(strPackage)));
      }
 else {
        String strName=anImportDeclaration.getImportedName();
        String fieldName=strName.substring(strName.lastIndexOf('.') + 1);
        Class<?> staticClassWithField=loadClass(strPackage);
        if (staticClassWithField != null) {
          JavaTypeDefinition typeDef=getFieldType(JavaTypeDefinition.forClass(staticClassWithField),fieldName,currentAcu.getType());
          staticFieldImageToTypeDef.put(fieldName,typeDef);
        }
        List<JavaTypeDefinition> typeList=staticNamesToClasses.get(fieldName);
        if (typeList == null) {
          typeList=new ArrayList<>();
        }
        typeList.add(JavaTypeDefinition.forClass(staticClassWithField));
        staticNamesToClasses.put(fieldName,typeList);
      }
    }
 else {
      if (anImportDeclaration.isImportOnDemand()) {
        importedOnDemand.add(strPackage);
      }
 else {
        String strName=anImportDeclaration.getImportedName();
        importedClasses.put(strName,strName);
        importedClasses.put(strName.substring(strPackage.length() + 1),strName);
      }
    }
  }
}
