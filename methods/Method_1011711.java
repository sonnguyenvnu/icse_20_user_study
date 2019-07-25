@NotNull @Override public UpdateResult update(EditorComponent editorComponent,final boolean incremental,boolean applyQuickFixes,Cancellable cancellable){
  try {
    final SRepository repository=editorComponent.getEditorContext().getRepository();
    final SNode editedNode=editorComponent.getEditedNode();
    if (editedNode == null) {
      return UpdateResult.CANCELLED;
    }
    TypecheckingSession typecheckingSession=editorComponent.getTypecheckingSession();
    if (typecheckingSession == null) {
      return UpdateResult.CANCELLED;
    }
    TypecheckingFacade.getFromContext().runWithSession(typecheckingSession,new Runnable(){
      public void run(){
        doCreateMessages(editedNode,incremental,repository);
      }
    }
);
    return new UpdateResult.Completed(true,Collections.<EditorMessage>emptySet());
  }
 catch (  RuntimeException e) {
    myCurrentSession=null;
    throw e;
  }
}
