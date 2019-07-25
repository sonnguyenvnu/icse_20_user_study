public void init(){
  myMessageBusConnection=myProject.getMessageBus().connect();
  myMessageBusConnection.subscribe(EditorComponentCreateListener.EDITOR_COMPONENT_CREATION,myEditorComponentCreationHandler);
  if (RuntimeFlags.isTestMode()) {
    return;
  }
  for (  EditorComponent editor : EditorComponentUtil.getAllEditorComponents(myFileEditorManager,true)) {
    editorComponentCreated(editor);
  }
}
