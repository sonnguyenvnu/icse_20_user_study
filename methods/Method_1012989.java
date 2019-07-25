@Override public Optional<ValueCompletion> from(SwaggerCompletionHelper swaggerCompletionHelper,CompletionResultSet completionResultSet){
  if (swaggerCompletionHelper.hasPath("$.info.x-audience")) {
    return Optional.of(new AudienceValueCompletion(swaggerCompletionHelper,completionResultSet));
  }
 else {
    return Optional.empty();
  }
}
