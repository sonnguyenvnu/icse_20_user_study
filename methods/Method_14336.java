static public Column getOrAllocateColumn(Project project,List<String> currentFileColumnNames,int index,boolean hasOurOwnColumnNames){
  if (index < currentFileColumnNames.size()) {
    return project.columnModel.getColumnByName(currentFileColumnNames.get(index));
  }
 else   if (index >= currentFileColumnNames.size()) {
    String prefix="Column ";
    int i=index + 1;
    while (true) {
      String columnName=prefix + i;
      Column column=project.columnModel.getColumnByName(columnName);
      if (column != null) {
        if (hasOurOwnColumnNames) {
          i++;
        }
 else {
          return column;
        }
      }
 else {
        column=new Column(project.columnModel.allocateNewCellIndex(),columnName);
        try {
          project.columnModel.addColumn(project.columnModel.columns.size(),column,false);
        }
 catch (        ModelException e) {
        }
        currentFileColumnNames.add(columnName);
        return column;
      }
    }
  }
 else {
    throw new RuntimeException("Unexpected code path");
  }
}
