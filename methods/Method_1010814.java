@NotNull public UpdateResult update(final EditorComponent editorComponent,final boolean incremental,boolean applyQuickFixes,final Cancellable cancellable){
  final SNode node=editorComponent.getEditedNode();
  try {
    TypecheckingSession typecheckingSession=editorComponent.getTypecheckingSession();
    if (typecheckingSession == null) {
      return UpdateResult.CANCELLED;
    }
    Set<EditorMessage> messages=TypecheckingFacade.getFromContext().runWithSession(typecheckingSession,new Supplier<Set<EditorMessage>>(){
      @Override public Set<EditorMessage> get(){
        return doCreateMessages(node,incremental,editorComponent.getEditorContext(),cancellable);
      }
    }
);
    return new UpdateResult.Completed(myMessagesChanged,messages);
  }
 catch (  IndexNotReadyException e) {
    myErrorComponents.clear(editorComponent);
    throw e;
  }
}
