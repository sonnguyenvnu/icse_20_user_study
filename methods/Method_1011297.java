private List<SConcept> parameter(final SNode node,final EditorContext editorContext){
  return ListSequence.fromList(SConceptOperations.getAllSubConcepts2(SNodeOperations.getConcept(node),SNodeOperations.getModel(node))).where(new IWhereFilter<SConcept>(){
    public boolean accept(    SConcept it){
      return !(it.isAbstract());
    }
  }
).toListSequence();
}
