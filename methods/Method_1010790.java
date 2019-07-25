public void mark(List<? extends SimpleEditorMessage> messages){
  for (  SimpleEditorMessage message : messages) {
    mark(message);
  }
  repaintAndRebuildEditorMessages();
}
