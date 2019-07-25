private void rename(ClassNode owner,FieldNode field,TextField txtName){
  String text=txtName.getText();
  if (!txtName.isDisabled() && !text.equals(field.name)) {
    Bus.post(new FieldRenameEvent(owner,field,field.name,text));
    Bus.post(new ClassReloadEvent(owner.name));
    txtName.setDisable(true);
  }
}
