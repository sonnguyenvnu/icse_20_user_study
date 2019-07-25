@Override public void _commit(boolean finish){
  this.setChecked(this.myOptionsCheckBox.isSelected());
  this.setTextFieldText(this.myTextField.getText());
  int index=this.mySelectComboBox.getSelectedIndex();
  if (index >= 0) {
    this.setVariant(this.myVariantsArray[index]);
  }
}
