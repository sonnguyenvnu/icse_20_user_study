/** 
 * Check whether this seemingly duplicate import is actually a disambiguation import. Example: import java.awt.*; import java.util.*; import java.util.List; //Needed because java.awt.List exists
 */
private boolean isDisambiguationImport(ASTCompilationUnit node,String singleTypePkg,String singleTypeName){
  for (  ImportWrapper thisImportOnDemand : importOnDemandImports) {
    if (!thisImportOnDemand.getName().equals(singleTypePkg)) {
      if (!thisImportOnDemand.isStaticOnDemand()) {
        String fullyQualifiedClassName=thisImportOnDemand.getName() + "." + singleTypeName;
        if (node.getClassTypeResolver().classNameExists(fullyQualifiedClassName)) {
          return true;
        }
      }
 else {
        Class<?> importClass=node.getClassTypeResolver().loadClass(thisImportOnDemand.getName());
        if (importClass != null) {
          for (          Method m : importClass.getMethods()) {
            if (Modifier.isStatic(m.getModifiers()) && m.getName().equals(singleTypeName)) {
              return true;
            }
          }
        }
      }
    }
  }
  String fullyQualifiedClassName="java.lang." + singleTypeName;
  return node.getClassTypeResolver().classNameExists(fullyQualifiedClassName);
}
