private void usesMultifile(Node rootNode,LanguageVersionHandler languageVersionHandler,RuleSets ruleSets,Language language){
  if (ruleSets.usesMultifile(language)) {
    try (TimedOperation to=TimeTracker.startOperation(TimedOperationCategory.MULTIFILE_ANALYSIS)){
      languageVersionHandler.getMultifileFacade().start(rootNode);
    }
   }
}
