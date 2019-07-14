/** 
 * Returns true if the enclosing type of this type declaration is any of the given kinds. If this declaration is a top-level declaration, returns false. This won't consider anonymous classes until #905 is tackled. TODO 7.0.0
 * @param kinds Kinds to test
 */
public final boolean enclosingTypeIsA(TypeKind... kinds){
  ASTAnyTypeDeclaration parent=getEnclosingTypeDeclaration();
  if (parent == null) {
    return false;
  }
  for (  TypeKind k : kinds) {
    if (parent.getTypeKind() == k) {
      return true;
    }
  }
  return false;
}
