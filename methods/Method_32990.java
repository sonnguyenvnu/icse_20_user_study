/** 
 * @param forward true gets the column to the right, false the column to the left of the current column
 * @return
 */
private void editNext(boolean forward){
  List<TableColumn<S,?>> columns=new ArrayList<>();
  for (  TableColumn<S,?> column : getTableView().getColumns()) {
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
  TableColumn<S,?> nextColumn=columns.get(nextIndex);
  if (nextColumn != null) {
    getTableView().edit(index,nextColumn);
    getTableView().scrollToColumn(nextColumn);
  }
}
