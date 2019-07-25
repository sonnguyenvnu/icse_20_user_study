@Override public void apply() throws ConfigurationException {
  getSettings().methodSignatureSettings=new ArrayList<>(this.tableView.getListTableModel().getItems());
  getSettings().objectSignatureTypeProvider=enableCustomSignatureTypesCheckBox.isSelected();
  this.changed=false;
}
