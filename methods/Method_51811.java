/** 
 * Checks whether the type this node is referring to is declared within the same compilation unit - either a class/interface or a enum type. You want to check this, if  {@link #getType()} is null.
 * @return <code>true</code> if this node referencing a type in the samecompilation unit, <code>false</code> otherwise.
 */
public boolean isReferenceToClassSameCompilationUnit(){
  ASTCompilationUnit root=getFirstParentOfType(ASTCompilationUnit.class);
  for (  ASTClassOrInterfaceDeclaration c : root.findDescendantsOfType(ASTClassOrInterfaceDeclaration.class,true)) {
    if (c.hasImageEqualTo(getImage())) {
      return true;
    }
  }
  for (  ASTEnumDeclaration e : root.findDescendantsOfType(ASTEnumDeclaration.class,true)) {
    if (e.hasImageEqualTo(getImage())) {
      return true;
    }
  }
  return false;
}
