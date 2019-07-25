public void refresh(){
  if (myEditorComponent == null || myProject == null) {
    return;
  }
  final SNode selectedNode=myEditorComponent.getSelectedNode();
  if (selectedNode == null) {
    return;
  }
  showTraceForNode(myProject,selectedNode,myEditorComponent);
  this.validate();
}
