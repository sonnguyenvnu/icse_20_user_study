@Override public CompletableFuture<MaxwellDiagnosticResult.Check> check(){
  return getLatency().thenApply(this::normalResult).exceptionally(this::exceptionResult);
}
