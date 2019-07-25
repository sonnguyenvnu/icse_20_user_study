private List<IssueKindReportItem> parameter(final SNode node,final EditorContext editorContext){
  EditorComponent editorComponent=(EditorComponent)editorContext.getEditorComponent();
  Collection<IssueKindReportItem> reportItemsForCell=editorComponent.getReportItemsForCell(editorComponent.getSelectedCell());
  return ListSequence.fromListWithValues(new ArrayList<IssueKindReportItem>(),CollectionSequence.fromCollection(reportItemsForCell).where(new IWhereFilter<IssueKindReportItem>(){
    public boolean accept(    IssueKindReportItem it){
      return !(Objects.equals(it.getSeverity(),MessageStatus.ERROR));
    }
  }
));
}
