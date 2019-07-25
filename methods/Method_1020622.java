@Nonnull public static WorkRunner from(ExecutorService service){
  return new ExecutorServiceWorkRunner(checkNotNull(service));
}
