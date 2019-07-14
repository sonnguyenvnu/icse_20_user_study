/** 
 * Determines all the class/interface declarations inside this compilation unit, which implement Cloneable
 * @param currentClass the node of the class, that is currently analyzed (inside this compilation unit)
 * @return a Set of class/interface names
 */
private Set<String> determineTopLevelCloneableClasses(final ASTClassOrInterfaceDeclaration currentClass){
  final List<ASTClassOrInterfaceDeclaration> classes=currentClass.getFirstParentOfType(ASTCompilationUnit.class).findDescendantsOfType(ASTClassOrInterfaceDeclaration.class);
  final Set<String> classesNames=new HashSet<>();
  for (  final ASTClassOrInterfaceDeclaration c : classes) {
    if (!Objects.equals(c,currentClass) && extendsOrImplementsCloneable(c)) {
      classesNames.add(c.getImage());
    }
  }
  return classesNames;
}
