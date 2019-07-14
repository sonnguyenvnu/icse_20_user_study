@Override protected HistoryEntry createHistoryEntry(Project project,long historyEntryID) throws Exception {
  Engine engine=new Engine(project);
  engine.setMode(_mode);
  List<Integer> rowIndices=new ArrayList<Integer>();
  if (_mode == Mode.RowBased) {
    RowVisitor visitor=new IndexingVisitor(rowIndices);
    if (_sorting != null) {
      SortingRowVisitor srv=new SortingRowVisitor(visitor);
      srv.initializeFromConfig(project,_sorting);
      if (srv.hasCriteria()) {
        visitor=srv;
      }
    }
    engine.getAllRows().accept(project,visitor);
  }
 else {
    RecordVisitor visitor=new IndexingVisitor(rowIndices);
    if (_sorting != null) {
      SortingRecordVisitor srv=new SortingRecordVisitor(visitor);
      srv.initializeFromConfig(project,_sorting);
      if (srv.hasCriteria()) {
        visitor=srv;
      }
    }
    engine.getAllRecords().accept(project,visitor);
  }
  return new HistoryEntry(historyEntryID,project,"Reorder rows",this,new RowReorderChange(rowIndices));
}
