@Override protected String createDescription(Column column,List<CellChange> cellChanges){
  return "Clear recon data for " + cellChanges.size() + " cells containing \"" + _similarValue + "\" in column " + _columnName;
}
