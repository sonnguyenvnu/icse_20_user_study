@Override public void commitEdit(T newValue){
  super.commitEdit(newValue);
  getTableView().getProperties().remove(OnPressedEditableTableCell.class);
}
