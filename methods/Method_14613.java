@Override protected String createDescription(Column column,List<CellChange> cellChanges){
  return "Match specific item " + match.name + " (" + match.id + ") to " + cellChanges.size() + " cells in column " + column.getName();
}
