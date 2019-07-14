/** 
 * validates the value of the tree item, this method also hides the column value for the grouped nodes
 * @param param tree item
 * @return true if the value is valid else false
 */
public final boolean validateValue(CellDataFeatures<S,T> param){
  Object rowObject=param.getValue().getValue();
  return !((rowObject instanceof RecursiveTreeObject && rowObject.getClass() == RecursiveTreeObject.class) || (param.getTreeTableView() instanceof JFXTreeTableView && ((JFXTreeTableView<?>)param.getTreeTableView()).getGroupOrder().contains(this) && param.getValue().getParent() != null && param.getValue().getParent().getValue() != null && param.getValue().getParent().getValue().getClass() == RecursiveTreeObject.class));
}
