@Override protected String createDescription(Column column,List<CellChange> cellChanges){
  return (_clearData ? "Discard recon judgments and clear recon data" : "Discard recon judgments") + " for " + cellChanges.size() + " cells in column " + column.getName();
}
