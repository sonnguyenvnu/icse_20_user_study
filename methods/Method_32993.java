@Override public void cancelEdit(){
  editorNode=null;
  super.cancelEdit();
  builder.cancelEdit();
  builder.setValue(getValue());
  builder.nullEditorNode();
  setContentDisplay(ContentDisplay.TEXT_ONLY);
}
