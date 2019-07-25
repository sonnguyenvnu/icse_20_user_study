public Collection<IntentionExecutable> instances(final SNode node,final EditorContext context){
  List<IntentionExecutable> list=ListSequence.fromList(new ArrayList<IntentionExecutable>());
  List<IssueKindReportItem> paramList=parameter(node,context);
  if (paramList != null) {
    for (    IssueKindReportItem param : paramList) {
      ListSequence.fromList(list).addElement(new SuppressSpecificInspection_Intention.IntentionImplementation(param));
    }
  }
  return list;
}
