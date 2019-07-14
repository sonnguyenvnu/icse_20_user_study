static public void setupColumns(Project project,List<String> columnNames){
  Map<String,Integer> nameToIndex=new HashMap<String,Integer>();
  for (int c=0; c < columnNames.size(); c++) {
    String cell=columnNames.get(c).trim();
    if (cell.isEmpty()) {
      cell="Column";
    }
 else     if (cell.startsWith("\"") && cell.endsWith("\"")) {
      cell=cell.substring(1,cell.length() - 1).trim();
    }
    if (nameToIndex.containsKey(cell)) {
      int index=nameToIndex.get(cell);
      nameToIndex.put(cell,index + 1);
      cell=cell.contains(" ") ? (cell + " " + index) : (cell + index);
    }
 else {
      nameToIndex.put(cell,2);
    }
    columnNames.set(c,cell);
    if (project.columnModel.getColumnByName(cell) == null) {
      Column column=new Column(project.columnModel.allocateNewCellIndex(),cell);
      try {
        project.columnModel.addColumn(project.columnModel.columns.size(),column,false);
      }
 catch (      ModelException e) {
      }
    }
  }
}
