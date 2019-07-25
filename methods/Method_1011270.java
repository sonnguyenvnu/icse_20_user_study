private List<SNode> parameter(final SNode node,final EditorContext editorContext){
  SNode sourceNode=MacroIntentionsUtil.getContextNodeConcept(node);
  if (sourceNode == null) {
    return null;
  }
  return ListSequence.fromList(AbstractConceptDeclaration__BehaviorDescriptor.getPropertyDeclarations_idhEwILLM.invoke(sourceNode)).where(new IWhereFilter<SNode>(){
    public boolean accept(    SNode it){
      return (boolean)DataTypeDeclaration__BehaviorDescriptor.isSimpleBoolean_idhKtGpIQ.invoke(SLinkOperations.getTarget(it,MetaAdapterFactory.getReferenceLink(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xf979bd086bL,0xfc26f42fe5L,"dataType")));
    }
  }
).toListSequence();
}
