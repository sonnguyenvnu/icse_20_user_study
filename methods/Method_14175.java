protected void initializeFromConfig(Project project,ClustererConfig c){
  _project=project;
  String colname=c.getColumnName();
  for (  Column column : project.columnModel.columns) {
    if (column.getName().equals(colname)) {
      _colindex=column.getCellIndex();
    }
  }
}
