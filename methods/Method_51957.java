/** 
 * Counts the operations matching the signature mask in this class.
 * @param classNode The class on which to count
 * @param mask      The mask
 * @return The number of operations matching the signature mask
 */
protected int countMatchingOpSigs(ASTAnyTypeDeclaration classNode,JavaOperationSigMask mask){
  int count=0;
  List<ASTMethodOrConstructorDeclaration> decls=getMethodsAndConstructors(classNode);
  for (  ASTMethodOrConstructorDeclaration decl : decls) {
    if (mask.covers(decl.getSignature())) {
      count++;
    }
  }
  return count;
}
