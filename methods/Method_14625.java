@Override protected HistoryEntry createHistoryEntry(Project project,long historyEntryID) throws Exception {
  Engine engine=createEngine(project);
  List<Change> changes=new ArrayList<Change>(project.rows.size());
  FilteredRows filteredRows=engine.getAllFilteredRows();
  filteredRows.accept(project,createRowVisitor(project,changes));
  return new HistoryEntry(historyEntryID,project,(_starred ? "Star" : "Unstar") + " " + changes.size() + " rows",this,new MassChange(changes,false));
}
