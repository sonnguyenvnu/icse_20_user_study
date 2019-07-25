private boolean inspect(NodeEditorComponent editorComponent,SNode node){
  DataContext dataContext=DataManager.getInstance().getDataContext(editorComponent);
  FileEditor fileEditor=MPSCommonDataKeys.FILE_EDITOR.getData(dataContext);
  getInspector().inspect(node,fileEditor,editorComponent.getEditorHintsForNode(node));
  return true;
}
