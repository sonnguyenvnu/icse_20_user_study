private List<TreeTableColumn<S,?>> getLeaves(TreeTableColumn<S,?> root){
  List<TreeTableColumn<S,?>> columns=new ArrayList<>();
  if (root.getColumns().isEmpty()) {
    if (root.isEditable()) {
      columns.add(root);
    }
    return columns;
  }
 else {
    for (    TreeTableColumn<S,?> column : root.getColumns()) {
      columns.addAll(getLeaves(column));
    }
    return columns;
  }
}
