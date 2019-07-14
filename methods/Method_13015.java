protected void filter(Indexes indexes,String pattern,int flags,Set<Container.Entry> matchingEntries){
  boolean declarations=((flags & SearchInConstantPoolsView.SEARCH_DECLARATION) != 0);
  boolean references=((flags & SearchInConstantPoolsView.SEARCH_REFERENCE) != 0);
  if ((flags & SearchInConstantPoolsView.SEARCH_TYPE) != 0) {
    if (declarations)     match(indexes,"typeDeclarations",pattern,SearchInConstantPoolsController::matchTypeEntriesWithChar,SearchInConstantPoolsController::matchTypeEntriesWithString,matchingEntries);
    if (references)     match(indexes,"typeReferences",pattern,SearchInConstantPoolsController::matchTypeEntriesWithChar,SearchInConstantPoolsController::matchTypeEntriesWithString,matchingEntries);
  }
  if ((flags & SearchInConstantPoolsView.SEARCH_CONSTRUCTOR) != 0) {
    if (declarations)     match(indexes,"constructorDeclarations",pattern,SearchInConstantPoolsController::matchTypeEntriesWithChar,SearchInConstantPoolsController::matchTypeEntriesWithString,matchingEntries);
    if (references)     match(indexes,"constructorReferences",pattern,SearchInConstantPoolsController::matchTypeEntriesWithChar,SearchInConstantPoolsController::matchTypeEntriesWithString,matchingEntries);
  }
  if ((flags & SearchInConstantPoolsView.SEARCH_METHOD) != 0) {
    if (declarations)     match(indexes,"methodDeclarations",pattern,SearchInConstantPoolsController::matchWithChar,SearchInConstantPoolsController::matchWithString,matchingEntries);
    if (references)     match(indexes,"methodReferences",pattern,SearchInConstantPoolsController::matchWithChar,SearchInConstantPoolsController::matchWithString,matchingEntries);
  }
  if ((flags & SearchInConstantPoolsView.SEARCH_FIELD) != 0) {
    if (declarations)     match(indexes,"fieldDeclarations",pattern,SearchInConstantPoolsController::matchWithChar,SearchInConstantPoolsController::matchWithString,matchingEntries);
    if (references)     match(indexes,"fieldReferences",pattern,SearchInConstantPoolsController::matchWithChar,SearchInConstantPoolsController::matchWithString,matchingEntries);
  }
  if ((flags & SearchInConstantPoolsView.SEARCH_STRING) != 0) {
    if (declarations || references)     match(indexes,"strings",pattern,SearchInConstantPoolsController::matchWithChar,SearchInConstantPoolsController::matchWithString,matchingEntries);
  }
  if ((flags & SearchInConstantPoolsView.SEARCH_MODULE) != 0) {
    if (declarations)     match(indexes,"javaModuleDeclarations",pattern,SearchInConstantPoolsController::matchWithChar,SearchInConstantPoolsController::matchWithString,matchingEntries);
    if (references)     match(indexes,"javaModuleReferences",pattern,SearchInConstantPoolsController::matchWithChar,SearchInConstantPoolsController::matchWithString,matchingEntries);
  }
}
