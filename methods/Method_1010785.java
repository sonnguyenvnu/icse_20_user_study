private void inspect(final SNode toSelect){
  myLastInspectedNode=toSelect;
  if (getInspector() == null) {
    return;
  }
  DataContext dataContext=DataManager.getInstance().getDataContext(this);
  FileEditor fileEditor=MPSCommonDataKeys.FILE_EDITOR.getData(dataContext);
  String[] inspectorInitialEditorHints=getEditorHintsForNode(toSelect);
  if (getInspectorTool() != null) {
    getInspectorTool().inspect(toSelect,fileEditor,inspectorInitialEditorHints);
  }
}
