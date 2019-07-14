/** 
 * Retrieves cause exception and wraps to  {@link CommandActionExecutionException}.
 * @param throwable the throwable
 */
public static void propagateCause(Throwable throwable) throws CommandActionExecutionException {
  throw new CommandActionExecutionException(throwable.getCause());
}
