private void checkImports(AbstractJavaTypeNode node,Object data){
  String name=node.getImage();
  List<ASTImportDeclaration> matches=new ArrayList<>();
  for (  ASTImportDeclaration importDeclaration : imports) {
    if (!importDeclaration.isImportOnDemand()) {
      if (name.equals(importDeclaration.getImportedName())) {
        matches.add(importDeclaration);
        continue;
      }
    }
    if (declarationMatches(importDeclaration,name)) {
      matches.add(importDeclaration);
    }
  }
  if (matches.isEmpty()) {
    for (    ASTImportDeclaration importDeclaration : imports) {
      String[] importParts=importDeclaration.getImportedName().split("\\.");
      String[] nameParts=name.split("\\.");
      if (importDeclaration.isStatic()) {
        if (importDeclaration.isImportOnDemand()) {
          if (nameParts[nameParts.length - 2].equals(importParts[importParts.length - 1])) {
            matches.add(importDeclaration);
          }
        }
 else {
          if (nameParts[nameParts.length - 1].equals(importParts[importParts.length - 1]) && nameParts[nameParts.length - 2].equals(importParts[importParts.length - 2])) {
            matches.add(importDeclaration);
          }
        }
      }
 else       if (!importDeclaration.isImportOnDemand()) {
        if (nameParts[nameParts.length - 1].equals(importParts[importParts.length - 1])) {
          matches.add(importDeclaration);
        }
 else         if (couldBeMethodCall(node) && nameParts.length > 1 && nameParts[nameParts.length - 2].equals(importParts[importParts.length - 1])) {
          matches.add(importDeclaration);
        }
      }
    }
  }
  if (matches.isEmpty()) {
    if (isJavaLangImplicit(node)) {
      addViolation(data,node,new Object[]{node.getImage(),"java.lang.*","implicit "});
    }
 else     if (isSamePackage(node)) {
      addViolation(data,node,new Object[]{node.getImage(),currentPackage + ".*","same package "});
    }
  }
 else {
    ASTImportDeclaration firstMatch=findFirstMatch(matches);
    if (!isAvoidingConflict(node,name,firstMatch)) {
      String importStr=firstMatch.getImportedName() + (firstMatch.isImportOnDemand() ? ".*" : "");
      String type=firstMatch.isStatic() ? "static " : "";
      addViolation(data,node,new Object[]{node.getImage(),importStr,type});
    }
  }
}
