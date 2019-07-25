@Override public int update(){
  session.commit(true);
  Table view=getSchema().findTableOrView(session,viewName);
  if (view == null) {
    if (!ifExists) {
      throw DbException.get(ErrorCode.VIEW_NOT_FOUND_1,viewName);
    }
  }
 else {
    if (TableType.VIEW != view.getTableType()) {
      throw DbException.get(ErrorCode.VIEW_NOT_FOUND_1,viewName);
    }
    session.getUser().checkRight(view,Right.ALL);
    if (dropAction == ConstraintActionType.RESTRICT) {
      for (      DbObject child : view.getChildren()) {
        if (child instanceof TableView) {
          throw DbException.get(ErrorCode.CANNOT_DROP_2,viewName,child.getName());
        }
      }
    }
    TableView tableView=(TableView)view;
    ArrayList<Table> copyOfDependencies=new ArrayList<>(tableView.getTables());
    view.lock(session,true,true);
    session.getDatabase().removeSchemaObject(session,view);
    for (    Table childTable : copyOfDependencies) {
      if (TableType.VIEW == childTable.getTableType()) {
        TableView childTableView=(TableView)childTable;
        if (childTableView.isTableExpression() && childTableView.getName() != null) {
          session.getDatabase().removeSchemaObject(session,childTableView);
        }
      }
    }
    session.getDatabase().unlockMeta(session);
  }
  return 0;
}
