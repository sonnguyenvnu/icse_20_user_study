private void initialize(){
  this.getStyleClass().add(DEFAULT_STYLE_CLASS);
  try {
    editorProperty();
    Field editorField=DatePicker.class.getDeclaredField("editor");
    editorField.setAccessible(true);
    ReadOnlyObjectWrapper<TextField> editor=(ReadOnlyObjectWrapper<TextField>)editorField.get(this);
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
 catch (  NoSuchFieldException e) {
  }
catch (  IllegalAccessException e) {
  }
}
