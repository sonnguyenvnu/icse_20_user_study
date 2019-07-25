@NotNull @Override public UpdateResult update(final EditorComponent editorComponent,final boolean incremental,final boolean applyQuickFixes,final Cancellable cancellable){
  try {
    TypecheckingSession session=editorComponent.getTypecheckingSession();
    if (session == null)     return UpdateResult.CANCELLED;
    LegacyTypecheckingQueries legacyTypesystemQueries=session.getQueries(LegacyTypecheckingProvider.class);
    TypeCheckingContext typeCheckingContext=legacyTypesystemQueries.getTypeCheckingContext();
    return ((IncrementalTypecheckingContext)typeCheckingContext).runTypeCheckingAction(() -> doCreateMessages(typeCheckingContext,incremental,editorComponent.getEditorContext(),editorComponent.getEditedNode(),cancellable,applyQuickFixes));
  }
 catch (  IndexNotReadyException e) {
    if (editorComponent.getNodeForTypechecking() != null) {
      TypecheckingSession session=editorComponent.getTypecheckingSession();
      LegacyTypecheckingQueries legacyTypesystemQueries=session.getQueries(LegacyTypecheckingProvider.class);
      legacyTypesystemQueries.getTypeCheckingContext().clear();
    }
    throw e;
  }
}
