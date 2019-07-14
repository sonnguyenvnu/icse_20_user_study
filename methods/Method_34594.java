private static HystrixCommandExecutionHook initExecutionHook(HystrixCommandExecutionHook fromConstructor){
  if (fromConstructor == null) {
    return new ExecutionHookDeprecationWrapper(HystrixPlugins.getInstance().getCommandExecutionHook());
  }
 else {
    if (fromConstructor instanceof ExecutionHookDeprecationWrapper) {
      return fromConstructor;
    }
 else {
      return new ExecutionHookDeprecationWrapper(fromConstructor);
    }
  }
}
