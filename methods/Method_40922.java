@SuppressWarnings("unchecked") static <R>EventListener ofAttempt(CheckedConsumer<? extends ExecutionAttemptedEvent<R>> handler){
  return (  Object result,  Throwable failure,  ExecutionContext context) -> {
    try {
      ((CheckedConsumer<ExecutionAttemptedEvent<R>>)handler).accept(new ExecutionAttemptedEvent<>((R)result,failure,context));
    }
 catch (    Exception ignore) {
    }
  }
;
}
