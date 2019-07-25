private List<SNode> parameter(final SNode node,final EditorContext editorContext){
  SNode sourceNode=MacroIntentionsUtil.getContextNodeConcept(node);
  return MacroIntentionsUtil.getLinks(sourceNode,false);
}
