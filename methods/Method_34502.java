/** 
 * Retrieves cause exception and wraps to  {@link CommandActionExecutionException}.
 * @param throwable the throwable
 */
private void propagateCause(Throwable throwable) throws CommandActionExecutionException {
  ExceptionUtils.propagateCause(throwable);
}
