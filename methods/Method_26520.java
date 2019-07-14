/** 
 * Creates a  {@link Fix} that replaces wildcard imports. 
 */
static Fix createFix(ImmutableList<ImportTree> wildcardImports,Set<TypeToImport> typesToImport,VisitorState state){
  Map<Symbol,List<TypeToImport>> toFix=typesToImport.stream().collect(Collectors.groupingBy(TypeToImport::owner));
  final SuggestedFix.Builder fix=SuggestedFix.builder();
  for (  ImportTree importToDelete : wildcardImports) {
    String importSpecification=importToDelete.getQualifiedIdentifier().toString();
    if (importToDelete.isStatic()) {
      fix.removeStaticImport(importSpecification);
    }
 else {
      fix.removeImport(importSpecification);
    }
  }
  for (  Map.Entry<Symbol,List<TypeToImport>> entry : toFix.entrySet()) {
    final Symbol owner=entry.getKey();
    if (entry.getKey().getKind() != ElementKind.PACKAGE && entry.getValue().size() > MAX_MEMBER_IMPORTS) {
      qualifiedNameFix(fix,owner,state);
    }
 else {
      for (      TypeToImport toImport : entry.getValue()) {
        toImport.addFix(fix);
      }
    }
  }
  return fix.build();
}
