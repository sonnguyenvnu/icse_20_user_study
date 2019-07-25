@Override public boolean select(@NotNull EditorContext editorContext,@NotNull EditorCell editorCell){
  editorContext.getSelectionManager().setSelection(editorCell);
  return editorContext.getSelectionManager().getSelection() != null;
}
