/** 
 * Returns true if the metric can be computed on this type declaration. By default, annotation and interface declarations are filtered out.
 * @param node The type declaration
 * @return True if the metric can be computed on this type declaration
 */
@Override public boolean supports(ASTAnyTypeDeclaration node){
  return node.getTypeKind() != TypeKind.ANNOTATION && node.getTypeKind() != TypeKind.INTERFACE;
}
