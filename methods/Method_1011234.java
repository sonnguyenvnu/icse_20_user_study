private List<SAbstractConcept> parameter(final SNode node,final EditorContext editorContext){
  return ListSequence.fromList(SConceptOperations.getAllSubConcepts(MetaAdapterFactory.getInterfaceConcept(0x18bc659203a64e29L,0xa83a7ff23bde13baL,0x4e0f93d8a0c11832L,"jetbrains.mps.lang.editor.structure.ITransformationMenu"),SNodeOperations.getModel(node))).where(new IWhereFilter<SAbstractConcept>(){
    public boolean accept(    SAbstractConcept it){
      return !(it.isAbstract()) && it != SNodeOperations.getConcept(node);
    }
  }
).toListSequence();
}
