@Override public void cancelEdit(){
  super.cancelEdit();
  getTableView().getProperties().remove(OnPressedEditableTableCell.class);
}
