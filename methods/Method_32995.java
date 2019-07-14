@Override public void updateItem(T item,boolean empty){
  super.updateItem(item,empty);
  if (empty) {
    setText(null);
    setGraphic(null);
  }
 else {
    if (isEditing() && checkGroupedColumn()) {
      if (editorNode != null) {
        builder.setValue(getValue());
      }
      setGraphic(editorNode);
      setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
      builder.updateItem(item,empty);
    }
 else {
      Object value=getValue();
      if (value instanceof Node) {
        setGraphic((Node)value);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
      }
 else {
        setText(value == null ? null : String.valueOf(value));
        setContentDisplay(ContentDisplay.TEXT_ONLY);
      }
    }
  }
}
