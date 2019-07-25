private void rename(ClassNode node,TextField txtName){
  String text=txtName.getText();
  if (!text.equals(node.superName)) {
    setValue(text);
    Bus.post(new ClassReloadEvent(node.name,node.name));
    Bus.post(new ClassHierarchyUpdateEvent());
  }
}
