private List<TableColumn<S,?>> getLeaves(TableColumn<S,?> rootColumn){
  List<TableColumn<S,?>> columns=new ArrayList<>();
  if (rootColumn.getColumns().isEmpty()) {
    if (rootColumn.isEditable()) {
      columns.add(rootColumn);
    }
    return columns;
  }
 else {
    for (    TableColumn<S,?> column : rootColumn.getColumns()) {
      columns.addAll(getLeaves(column));
    }
    return columns;
  }
}
