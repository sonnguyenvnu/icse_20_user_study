/** 
 * Counts the fields matching the signature mask in this class.
 * @param classNode The class on which to count
 * @param mask      The mask
 * @return The number of fields matching the signature mask
 */
protected int countMatchingFieldSigs(ASTAnyTypeDeclaration classNode,JavaFieldSigMask mask){
  int count=0;
  List<ASTFieldDeclaration> decls=getFields(classNode);
  for (  ASTFieldDeclaration decl : decls) {
    if (mask.covers(decl.getSignature())) {
      count++;
    }
  }
  return count;
}
