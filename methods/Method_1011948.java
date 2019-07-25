private void focus(Editor nodeEditor,boolean cellInInspector){
  if (!cellInInspector) {
    final ToolWindowManager manager=ToolWindowManager.getInstance(myProject.getProject());
    manager.activateEditorComponent();
    Component toBeFocused;
    if (nodeEditor.getCurrentEditorComponent() != null) {
      toBeFocused=(Component)nodeEditor.getCurrentEditorComponent();
    }
 else {
      toBeFocused=((BaseNodeEditor)nodeEditor).getComponent();
    }
    getFocusManager().requestFocus(toBeFocused,false);
  }
 else {
    final InspectorTool inspectorTool=getInspector();
    inspectorTool.getToolWindow().activate(null);
    getFocusManager().requestFocus(inspectorTool.getInspector(),false);
  }
}
