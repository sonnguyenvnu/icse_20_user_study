public Collection<IntentionExecutable> instances(final SNode node,final EditorContext context){
  List<IntentionExecutable> list=ListSequence.fromList(new ArrayList<IntentionExecutable>());
  List<SAbstractConcept> paramList=parameter(node,context);
  if (paramList != null) {
    for (    SAbstractConcept param : paramList) {
      ListSequence.fromList(list).addElement(new AlterStatementListContainer_Intention.IntentionImplementation(param));
    }
  }
  return list;
}
