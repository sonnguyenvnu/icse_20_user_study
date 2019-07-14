/** 
 * @return true if the column is grouped else false
 */
public boolean isGrouped(){
  return getTreeTableView() instanceof JFXTreeTableView && ((JFXTreeTableView<?>)getTreeTableView()).getGroupOrder().contains(this);
}
