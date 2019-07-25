@Override public boolean visit(QualifiedName qualifiedName){
  Name qualifier=qualifiedName.getQualifier();
  while (!qualifier.isSimpleName()) {
    qualifier=((QualifiedName)qualifier).getQualifier();
  }
  referencedNames.add(qualifier.getFullyQualifiedName());
  return false;
}
