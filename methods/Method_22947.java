void apply(Editor editor){
  editor.setBounds(editorBounds);
  if (dividerLocation == 0) {
    dividerLocation=2 * editor.getSize().height / 3;
  }
  editor.setDividerLocation(dividerLocation);
  if (isMaximized) {
    editor.setExtendedState(Frame.MAXIMIZED_BOTH);
  }
}
