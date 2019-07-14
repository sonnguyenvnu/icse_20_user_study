@Override protected String createDescription(Column column,List<CellChange> cellChanges){
  return "Mark to create new items for " + cellChanges.size() + " cells in column " + column.getName() + (_shareNewTopics ? ", one item for each group of similar cells" : ", one item for each cell");
}
