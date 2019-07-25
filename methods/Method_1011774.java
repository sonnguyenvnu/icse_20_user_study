@Override public void rehighlight(){
  ChangeSetBuilder.rebuildChangeSet(myChangeSet);
  myNewEditor.unhighlightAllChanges();
  myOldEditor.unhighlightAllChanges();
  if (myNewEditor.getEditedNode() == null) {
    myNewEditor.editRoot(myRootId,myChangeSet.getNewModel());
  }
  myNewEditor.getMainEditor().rebuildEditorContent();
  myOldEditor.getMainEditor().rebuildEditorContent();
  highlightAllChanges();
}
