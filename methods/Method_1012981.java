@Override public Optional<FieldCompletion> from(SwaggerCompletionHelper swaggerCompletionHelper,CompletionResultSet completionResultSet){
  if (swaggerCompletionHelper.hasPath("$.info")) {
    return Optional.of(new InfoCompletion(swaggerCompletionHelper,completionResultSet));
  }
 else {
    return Optional.empty();
  }
}
