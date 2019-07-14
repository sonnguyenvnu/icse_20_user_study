public static InferredNullability getInferredNullability(Tree methodOrInitializerOrLambda){
  checkArgument(methodOrInitializerOrLambda instanceof MethodTree || methodOrInitializerOrLambda instanceof LambdaExpressionTree || methodOrInitializerOrLambda instanceof BlockTree || methodOrInitializerOrLambda instanceof VariableTree,"Tree `%s` is not a lambda, initializer, or method.",methodOrInitializerOrLambda);
  try {
    return inferenceCache.getUnchecked(methodOrInitializerOrLambda);
  }
 catch (  UncheckedExecutionException e) {
    throw e.getCause() instanceof CompletionFailure ? (CompletionFailure)e.getCause() : e;
  }
}
