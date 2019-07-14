@Override public Object getField(String name,Properties bindings){
  Column column=project.columnModel.getColumnByName(name);
  if (column != null) {
    int cellIndex=column.getCellIndex();
    Cell cell=row.getCell(cellIndex);
    if (cell != null) {
      return new WrappedCell(project,name,cell);
    }
  }
  return null;
}
