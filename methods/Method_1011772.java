public void rehighlight(){
  if (myDisposed) {
    return;
  }
  myMineEditor.unhighlightAllChanges();
  myResultEditor.unhighlightAllChanges();
  myRepositoryEditor.unhighlightAllChanges();
  if (myResultEditor.getEditedNode() == null) {
    SModel resultModel=myMergeSession.getResultModel();
    SNodeId nodeId=getRootNodeId(resultModel);
    if (nodeId != null) {
      myResultEditor.editRoot(nodeId,resultModel);
    }
  }
  myResultEditor.getMainEditor().rebuildEditorContent();
  highlightAllChanges();
}
