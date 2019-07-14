private boolean isAvoidingConflict(final AbstractJavaTypeNode node,final String name,final ASTImportDeclaration firstMatch){
  if (firstMatch.isImportOnDemand() && firstMatch.isStatic()) {
    final String methodCalled=name.substring(name.indexOf('.') + 1);
    for (    final ASTImportDeclaration importDeclaration : imports) {
      if (!Objects.equals(importDeclaration,firstMatch) && importDeclaration.isStatic()) {
        if (declarationMatches(firstMatch,importDeclaration.getImportedName())) {
          continue;
        }
        if (importDeclaration.isImportOnDemand()) {
          if (importDeclaration.getType() != null) {
            for (            final Method m : importDeclaration.getType().getMethods()) {
              if (m.getName().equals(methodCalled)) {
                return true;
              }
            }
          }
        }
 else         if (importDeclaration.getImportedName().endsWith(methodCalled)) {
          return true;
        }
      }
    }
  }
  final String unqualifiedName=name.substring(name.lastIndexOf('.') + 1);
  final int unqualifiedNameLength=unqualifiedName.length();
  if (firstMatch.isImportOnDemand() && !firstMatch.isStatic()) {
    for (    ASTImportDeclaration importDeclaration : imports) {
      if (importDeclaration != firstMatch && !importDeclaration.isStatic() && !importDeclaration.isImportOnDemand()) {
        if (!importDeclaration.getPackageName().equals(firstMatch.getPackageName()) && importDeclaration.getImportedSimpleName().equals(unqualifiedName)) {
          return true;
        }
      }
    }
  }
  String importName=firstMatch.getImportedName();
  String importUnqualified=importName.substring(importName.lastIndexOf('.') + 1);
  if (!firstMatch.isImportOnDemand() && !firstMatch.isStatic()) {
    if (!firstMatch.getImportedName().equals(name) && importUnqualified.equals(unqualifiedName)) {
      return true;
    }
  }
  if (couldBeMethodCall(node)) {
    String[] nameParts=name.split("\\.");
    String fqnName=name.substring(0,name.lastIndexOf('.'));
    if (!fqnName.equals(importName) && !firstMatch.isStatic() && !firstMatch.isImportOnDemand() && nameParts.length > 1 && nameParts[nameParts.length - 2].equals(importUnqualified)) {
      return true;
    }
  }
  final Set<String> qualifiedTypes=node.getScope().getEnclosingScope(SourceFileScope.class).getQualifiedTypeNames().keySet();
  for (  final String qualified : qualifiedTypes) {
    int fullLength=qualified.length();
    if (qualified.endsWith(unqualifiedName) && (fullLength == unqualifiedNameLength || qualified.charAt(fullLength - unqualifiedNameLength - 1) == '.')) {
      return true;
    }
  }
  return false;
}
