private List<SNode> parameter(final SNode node,final EditorContext editorContext){
  return ListSequence.fromList(AttributeOperations.getAttributeList(node,new IAttributeDescriptor.NodeAttribute(MetaAdapterFactory.getConcept(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x3a98b0957fe8e5d2L,"jetbrains.mps.lang.core.structure.SuppressErrorsAnnotation")))).where(new IWhereFilter<SNode>(){
    public boolean accept(    SNode it){
      return isNotEmptyString(SPropertyOperations.getString(it,MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x3a98b0957fe8e5d2L,0x7701afb3667b38f5L,"message")));
    }
  }
).toListSequence();
}
