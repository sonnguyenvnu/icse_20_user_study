private void rename(ClassNode node,TextField txtName){
  String text=txtName.getText();
  if (!txtName.isDisabled() && !text.equals(node.name)) {
    Bus.post(new ClassRenameEvent(node,node.name,text));
    Bus.post(new ClassReloadEvent(node.name,text));
    Bus.post(new ClassHierarchyUpdateEvent());
    txtName.setDisable(true);
  }
}
