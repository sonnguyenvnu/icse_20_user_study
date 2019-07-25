private LookupElementBuilder create(final Value value,final InsertHandler<LookupElement> insertHandler){
  return LookupElementBuilder.create(value.getValue()).withInsertHandler(insertHandler);
}
