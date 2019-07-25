private List<SNode> parameter(final SNode node,final EditorContext editorContext){
  SNode sourceNodeConcept=MacroIntentionsUtil.getContextNodeConcept(node);
  if (sourceNodeConcept == null) {
    return null;
  }
  final String p=EditingUtil.getEditedPropertyName(editorContext.getSelectedCell());
  if (p == null) {
    return null;
  }
  List<SNode> result=ListSequence.fromList(new ArrayList<SNode>());
  final SNode propertyDeclaration=ListSequence.fromList(AbstractConceptDeclaration__BehaviorDescriptor.getPropertyDeclarations_idhEwILLM.invoke(SNodeOperations.asNode(SNodeOperations.getConcept(node)))).where(new IWhereFilter<SNode>(){
    public boolean accept(    SNode it){
      return Objects.equals(SPropertyOperations.getString(it,MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name")),p);
    }
  }
).first();
  if (propertyDeclaration == null) {
    return result;
  }
  return ListSequence.fromList(AbstractConceptDeclaration__BehaviorDescriptor.getPropertyDeclarations_idhEwILLM.invoke(sourceNodeConcept)).where(new IWhereFilter<SNode>(){
    public boolean accept(    SNode it){
      return SLinkOperations.getTarget(it,MetaAdapterFactory.getReferenceLink(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xf979bd086bL,0xfc26f42fe5L,"dataType")) == SLinkOperations.getTarget(propertyDeclaration,MetaAdapterFactory.getReferenceLink(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xf979bd086bL,0xfc26f42fe5L,"dataType"));
    }
  }
).toListSequence();
}
