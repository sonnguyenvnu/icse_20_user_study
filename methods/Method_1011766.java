public void inspect(SNode node){
  String[] initialEditorHints=myMainEditorComponent.getEditorHintsForNode(node);
  boolean needToEdit=myInspector.getUpdater().setInitialEditorHints(initialEditorHints);
  if (needToEdit || myInspector.getEditedNode() != node) {
    myInspector.editNode(node);
    myInspector.getHighlightManager().repaintAndRebuildEditorMessages();
  }
}
