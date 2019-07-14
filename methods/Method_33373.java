private Object validateComboBox(Object text){
  if (control instanceof ComboBox && ((ComboBox)control).isEditable()) {
    final String editorText=((ComboBox<?>)control).getEditor().getText();
    text=editorText == null || editorText.isEmpty() ? null : text;
  }
  return text;
}
