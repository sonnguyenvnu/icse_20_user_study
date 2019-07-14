/** 
 * @param forward true gets the column to the right, false the column to the left of the current column
 * @return
 */
private void editNext(boolean forward){
  List<TreeTableColumn<S,?>> columns=new ArrayList<>();
  for (  TreeTableColumn<S,?> column : getTreeTableView().getColumns()) {
    columns.addAll(getLeaves(column));
  }
  int index=getIndex();
  int nextIndex=columns.indexOf(getTableColumn());
  if (forward) {
    nextIndex++;
    if (nextIndex > columns.size() - 1) {
      nextIndex=0;
      index+=stepFunction.apply(index,1);
    }
  }
 else {
    nextIndex--;
    if (nextIndex < 0) {
      nextIndex=columns.size() - 1;
      index+=stepFunction.apply(index,-1);
    }
  }
  if (columns.size() < 2 && index == getIndex()) {
    return;
  }
  TreeTableColumn<S,?> nextColumn=columns.get(nextIndex);
  if (nextColumn != null) {
    getTreeTableView().edit(index,nextColumn);
    getTreeTableView().scrollToColumn(nextColumn);
  }
}
