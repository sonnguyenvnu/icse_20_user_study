private List<SNode> parameter(final SNode node,final EditorContext editorContext){
  SNode sourceNode=MacroIntentionsUtil.getContextNodeConcept(node);
  if (sourceNode == null) {
    return null;
  }
  List<SNode> result=ListSequence.fromList(new ArrayList<SNode>());
  for (  SNode child : AbstractConceptDeclaration__BehaviorDescriptor.getLinkDeclarations_idhEwILKK.invoke(sourceNode)) {
    if (SEnumOperations.isMember(SPropertyOperations.getEnum(child,MetaAdapterFactory.getProperty(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xf979bd086aL,0xf98054bb04L,"sourceCardinality")),0xfc6f3944c3L) || SEnumOperations.isMember(SPropertyOperations.getEnum(child,MetaAdapterFactory.getProperty(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xf979bd086aL,0xf98054bb04L,"sourceCardinality")),0xfc6f3944c4L)) {
      ListSequence.fromList(result).addElement(child);
    }
  }
  return result;
}
