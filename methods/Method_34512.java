/** 
 * Wraps cause exception to  {@link CommandActionExecutionException}.
 * @param throwable the throwable
 */
public static CommandActionExecutionException wrapCause(Throwable throwable){
  return new CommandActionExecutionException(throwable.getCause());
}
