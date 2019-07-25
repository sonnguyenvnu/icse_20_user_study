private void delete(){
  if (tableView.getSelectionModel().getSelectedItem() != null) {
    XMPPrivacyFilter tableRow=tableView.getSelectionModel().getSelectedItem();
    fields.remove(tableRow);
  }
}
