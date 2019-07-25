@Override public void execute(final ToolComponent.IItem item){
  EditorComponent editorComponent=((EditorComponent)myLastSelection.getEditorComponent());
switch (item.getCommandPolicy()) {
case COMMAND_REQUIRED:
    editorComponent.getEditorContext().getRepository().getModelAccess().executeCommand(new EditorCommand(editorComponent){
      protected void doExecute(){
        item.execute();
      }
    }
);
  break;
case COMMAND_UNSUPPORTED:
item.execute();
break;
default :
throw new IllegalArgumentException("Unknown command policy " + item.getCommandPolicy());
}
editorComponent.requestFocusInWindow();
}
