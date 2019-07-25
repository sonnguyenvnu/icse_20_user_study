public Collection<IntentionExecutable> instances(final SNode node,final EditorContext context){
  List<IntentionExecutable> list=ListSequence.fromList(new ArrayList<IntentionExecutable>());
  List<SConcept> paramList=parameter(node,context);
  if (paramList != null) {
    for (    SConcept param : paramList) {
      ListSequence.fromList(list).addElement(new ReplaceWithConcreteSubconcept_Intention.IntentionImplementation(param));
    }
  }
  return list;
}
