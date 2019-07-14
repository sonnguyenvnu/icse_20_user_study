private Map<String,Node> getSubTypes(String qualifyingName,Scope subType){
  Set<ClassNameDeclaration> classDeclarations=subType.getDeclarations(ClassNameDeclaration.class).keySet();
  if (classDeclarations.isEmpty()) {
    return Collections.emptyMap();
  }
  Map<String,Node> types=new HashMap<>((int)(classDeclarations.size() / 0.75f) + 1);
  for (  ClassNameDeclaration c : classDeclarations) {
    String typeName=c.getName();
    if (qualifyingName != null) {
      typeName=qualifyingName + "." + typeName;
    }
    types.put(typeName,c.getNode());
    types.putAll(getSubTypes(typeName,c.getScope()));
  }
  return types;
}
