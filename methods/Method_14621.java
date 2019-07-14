@Override protected HistoryEntry createHistoryEntry(Project project,long historyEntryID) throws Exception {
  Engine engine=createEngine(project);
  List<Integer> rowIndices=new ArrayList<Integer>();
  FilteredRows filteredRows=engine.getAllFilteredRows();
  filteredRows.accept(project,createRowVisitor(project,rowIndices));
  return new HistoryEntry(historyEntryID,project,"Remove " + rowIndices.size() + " rows",this,new RowRemovalChange(rowIndices));
}
