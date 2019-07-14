@Override protected HistoryEntry createHistoryEntry(Project project,long historyEntryID) throws Exception {
  List<Row> newRows=new ArrayList<Row>();
  List<Row> oldRows=project.rows;
  for (int r=0; r < oldRows.size(); r++) {
    Row oldRow=oldRows.get(r);
    Row newRow=null;
    RowDependency rd=project.recordModel.getRowDependency(r);
    if (rd.cellDependencies != null) {
      newRow=oldRow.dup();
      for (      CellDependency cd : rd.cellDependencies) {
        if (cd != null) {
          int contextRowIndex=cd.rowIndex;
          int contextCellIndex=cd.cellIndex;
          if (contextRowIndex >= 0 && contextRowIndex < oldRows.size()) {
            Row contextRow=oldRows.get(contextRowIndex);
            Cell contextCell=contextRow.getCell(contextCellIndex);
            newRow.setCell(contextCellIndex,contextCell);
          }
        }
      }
    }
    newRows.add(newRow != null ? newRow : oldRow);
  }
  return new HistoryEntry(historyEntryID,project,getBriefDescription(project),DenormalizeOperation.this,new MassRowChange(newRows));
}
