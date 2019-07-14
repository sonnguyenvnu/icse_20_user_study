private void validateCompletableReturnType(Method commandMethod,Class<?> callbackReturnType){
  if (Void.TYPE == callbackReturnType) {
    throw new FallbackDefinitionException(createErrorMsg(commandMethod,method,"fallback cannot return 'void' if command return type is " + Completable.class.getSimpleName()));
  }
}
