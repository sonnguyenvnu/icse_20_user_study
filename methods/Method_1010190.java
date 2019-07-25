private void initialize() throws TemplateProcessingFailureException {
  if (myNodeAndMappingNamePairs != null) {
    return;
  }
  List<SNode> fragments=checkAdjacentFragments();
  List<Pair<SNode,String>> result=new ArrayList<>(fragments.size());
  for (  SNode fragment : fragments) {
    result.add(new Pair<>(SNodeOperations.getParent(fragment),GeneratorUtilEx.getMappingName_TemplateFragment(fragment,null)));
  }
  myNodeAndMappingNamePairs=result;
}
