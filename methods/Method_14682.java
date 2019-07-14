protected int getCellIndex(Project project){
  if (cellIndex == -2) {
    Column column=project.columnModel.getColumnByName(columnName);
    cellIndex=column != null ? column.getCellIndex() : -1;
  }
  return cellIndex;
}
