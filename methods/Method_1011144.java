private List<SNode> parameter(final SNode node,final EditorContext editorContext){
  return ListSequence.fromList(SModelOperations.rootsIncludingImported(SNodeOperations.getModel(node),MetaAdapterFactory.getConcept(0xc7d5b9dda05f4be2L,0xbc73f2e16994cc67L,0x340eb2bd2e03d160L,"jetbrains.mps.baseLanguage.lightweightdsl.structure.DSLDescriptor"))).where(new IWhereFilter<SNode>(){
    public boolean accept(    SNode it){
      return !(SNodeOperations.is(DSLDescriptor__BehaviorDescriptor.getPreferredConcept_id1_lSsE3TA5X.invoke(it),new SNodePointer("r:00000000-0000-4000-0000-011c895902ca(jetbrains.mps.baseLanguage.structure)","1068390468198")));
    }
  }
).toListSequence();
}
