/** 
 * Return true if the given symbol is defined in the current package. 
 */
public static boolean inSamePackage(Symbol targetSymbol,VisitorState state){
  JCCompilationUnit compilationUnit=(JCCompilationUnit)state.getPath().getCompilationUnit();
  PackageSymbol usePackage=compilationUnit.packge;
  PackageSymbol targetPackage=targetSymbol.packge();
  return targetPackage != null && usePackage != null && targetPackage.getQualifiedName().equals(usePackage.getQualifiedName());
}
