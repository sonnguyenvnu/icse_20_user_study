public final ReadOnlyObjectProperty<TextField> editorProperty(){
  if (editor == null) {
    editor=new ReadOnlyObjectWrapper<>(this,"editor");
    final FakeFocusJFXTextField editorNode=new FakeFocusJFXTextField();
    this.focusedProperty().addListener((obj,oldVal,newVal) -> {
      if (getEditor() != null) {
        editorNode.setFakeFocus(newVal);
      }
    }
);
    editorNode.activeValidatorWritableProperty().bind(activeValidatorProperty());
    editor.set(editorNode);
  }
  return editor.getReadOnlyProperty();
}
