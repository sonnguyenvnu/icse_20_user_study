public Collection<IntentionExecutable> instances(final SNode node,final EditorContext context){
  List<IntentionExecutable> list=ListSequence.fromList(new ArrayList<IntentionExecutable>());
  List<SNode> paramList=parameter(node,context);
  if (paramList != null) {
    for (    SNode param : paramList) {
      ListSequence.fromList(list).addElement(new AddNodeMacroParam_ifMacro_Intention.IntentionImplementation(param));
    }
  }
  return list;
}
