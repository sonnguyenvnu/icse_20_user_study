public void inspect(SNode node,FileEditor fileEditor,String[] enabledHints){
  if (node instanceof jetbrains.mps.smodel.SNode && !SNodeUtil.isAccessible(node,myInspectorComponent.getRepository())) {
    node=null;
  }
  myFileEditor=fileEditor;
  boolean needToEdit=myInspectorComponent.getUpdater().setInitialEditorHints(enabledHints);
  if (needToEdit || myInspectorComponent.getEditedNode() != node) {
    myInspectorComponent.editNode(node);
  }
  myMessagePanel.setNode(node);
}
