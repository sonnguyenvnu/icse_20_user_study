@Override public void _init(){
  this.mySelectComboBox.setModel(this.updateComboBoxModel());
  this.myOptionsCheckBox.setSelected(this.getChecked());
  this.myOptionsCheckBox.setEnabled(this.isCheckBoxEnabled());
  this.myTextField.setText(this.getTextFieldText());
  this.setEnabledState(this.myOptionsCheckBox.isSelected());
}
