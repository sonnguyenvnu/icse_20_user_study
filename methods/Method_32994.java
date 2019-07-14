/** 
 * only allows editing for items that are not grouped
 * @return whether the item is grouped or not
 */
private boolean checkGroupedColumn(){
  boolean allowEdit=true;
  if (getTreeTableRow().getTreeItem() != null) {
    Object rowObject=getTreeTableRow().getTreeItem().getValue();
    if (rowObject instanceof RecursiveTreeObject && rowObject.getClass() == RecursiveTreeObject.class) {
      allowEdit=false;
    }
 else {
      if (getTableColumn() instanceof JFXTreeTableColumn && ((JFXTreeTableColumn)getTableColumn()).isGrouped()) {
        if (getTreeTableRow().getTreeItem().getParent() != null && getTreeTableRow().getTreeItem().getParent().getValue().getClass() == RecursiveTreeObject.class) {
          allowEdit=false;
        }
      }
    }
  }
  return allowEdit;
}
