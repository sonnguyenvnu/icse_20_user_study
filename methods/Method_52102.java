private boolean isIterableModifiedInsideLoop(Entry<VariableNameDeclaration,List<NameOccurrence>> iterableInfo,ASTForStatement stmt){
  String iterableName=iterableInfo.getKey().getName();
  for (  NameOccurrence occ : iterableInfo.getValue()) {
    ASTForStatement forParent=occ.getLocation().getFirstParentOfType(ASTForStatement.class);
    if (Objects.equals(forParent,stmt)) {
      String image=occ.getLocation().getImage();
      if (image.startsWith(iterableName + ".remove")) {
        return true;
      }
    }
  }
  return false;
}
