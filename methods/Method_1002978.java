@Override public void run(){
  if (!EditorModificationUtil.checkModificationAllowed(editor)) {
    return;
  }
  if (!FileDocumentManager.getInstance().requestWriting(editor.getDocument(),project)) {
    return;
  }
  process(chooser.getSelectedElements());
}
