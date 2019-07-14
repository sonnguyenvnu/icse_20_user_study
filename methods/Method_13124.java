public void show(){
  SwingUtil.invokeLater(() -> {
    searchInConstantPoolsEnterTextField.selectAll();
    searchInConstantPoolsDialog.setVisible(true);
    searchInConstantPoolsEnterTextField.requestFocus();
  }
);
}
