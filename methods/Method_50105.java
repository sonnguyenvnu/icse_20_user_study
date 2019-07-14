/** 
 * Transforms an async ExecutionResult into a proto Messages. <p>Note that only data and not error information is represented in the Proto.
 */
public static <T extends Message>CompletableFuture<T> toProtoMessage(T message,CompletableFuture<ExecutionResult> executionResultCompletableFuture){
  return executionResultCompletableFuture.thenApply(executionResult -> QueryResponseToProto.buildMessage(message,executionResult.toSpecification()));
}
