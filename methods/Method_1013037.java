private LookupElementBuilder create(final Field field,final InsertHandler<LookupElement> insertHandler){
  return create(field).withInsertHandler(insertHandler);
}
