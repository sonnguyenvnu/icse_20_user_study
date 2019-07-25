private List<IssueKindReportItem> parameter(final SNode node,final EditorContext editorContext){
  EditorComponent editorComponent=(EditorComponent)editorContext.getEditorComponent();
  List<IssueKindReportItem> reportItemsForCell=ListSequence.fromListWithValues(new ArrayList<IssueKindReportItem>(),editorComponent.getReportItemsForCell(editorComponent.getSelectedCell()));
  if (SNodeOperations.hasRole(node,MetaAdapterFactory.getContainmentLink(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x10802efe25aL,0x47bf8397520e5942L,"smodelAttribute"))) {
    ListSequence.fromList(reportItemsForCell).addSequence(CollectionSequence.fromCollection(editorComponent.getReportItemsForCell(editorComponent.findNodeCell(SNodeOperations.getParent(node)))));
  }
  return ListSequence.fromListWithValues(new ArrayList<IssueKindReportItem>(),ListSequence.fromList(reportItemsForCell).where(new IWhereFilter<IssueKindReportItem>(){
    public boolean accept(    IssueKindReportItem it){
      return Objects.equals(it.getSeverity(),MessageStatus.ERROR);
    }
  }
));
}
