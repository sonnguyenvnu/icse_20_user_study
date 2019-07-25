public void rehighlight(){
  StructChangeSetBuilder.rebuildChangeSet(myChangeSet);
  myNewEditor.unhighlightAllChanges();
  myOldEditor.unhighlightAllChanges();
  if (myNewEditor.getEditedNode() == null) {
    myNewEditor.editNode(myChangeSet.getNewNodeId(),myChangeSet.getNewModel());
  }
  myNewEditor.getMainEditor().rebuildEditorContent();
  myOldEditor.getMainEditor().rebuildEditorContent();
  highlightAllChanges();
}
