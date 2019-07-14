private boolean isReplaceableIteratorLoop(Entry<VariableNameDeclaration,List<NameOccurrence>> indexInfo,ASTExpression guardCondition,Entry<VariableNameDeclaration,List<NameOccurrence>> iterableInfo,ASTForStatement stmt){
  if (isIterableModifiedInsideLoop(iterableInfo,stmt)) {
    return false;
  }
  String indexName=indexInfo.getKey().getName();
  if (indexName == null) {
    return false;
  }
  if (!guardCondition.hasDescendantMatchingXPath("./PrimaryExpression/PrimaryPrefix/Name[@Image='" + indexName + ".hasNext']")) {
    return false;
  }
  List<NameOccurrence> occurrences=indexInfo.getValue();
  if (occurrences.size() > 2) {
    return false;
  }
  for (  NameOccurrence occ : indexInfo.getValue()) {
    ScopedNode location=occ.getLocation();
    boolean isCallingNext=location instanceof ASTName && (location.hasImageEqualTo(indexName + ".hasNext") || location.hasImageEqualTo(indexName + ".next"));
    if (!isCallingNext) {
      return false;
    }
  }
  return true;
}
