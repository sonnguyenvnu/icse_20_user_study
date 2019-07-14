private double computeTextContentWidth(TextInputControl editor){
  Text text=new Text(editor.getText());
  text.setFont(editor.getFont());
  text.applyCss();
  return text.getLayoutBounds().getWidth();
}
