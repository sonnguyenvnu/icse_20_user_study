static protected ImportColumn getColumn(Project project,ImportColumnGroup columnGroup,String localName){
  if (columnGroup.columns.containsKey(localName)) {
    return columnGroup.columns.get(localName);
  }
  ImportColumn column=createColumn(project,columnGroup,localName);
  columnGroup.columns.put(localName,column);
  return column;
}
