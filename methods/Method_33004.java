@Override public void cancelEdit(){
  super.cancelEdit();
  getTreeTableView().getProperties().remove(OnPressedEditableTreeTableCell.class);
}
