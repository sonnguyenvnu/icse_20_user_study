/** 
 * Equivalent to  {@code new CommandLine(callable).execute(args)}, except for the return value.
 * @param callable the command to call when {@linkplain #parseArgs(String) parsing} succeeds.
 * @param args the command line arguments to parse
 * @param < C > the annotated object must implement Callable
 * @param < T > the return type of the most specific command (must implement {@code Callable})
 * @throws InitializationException if the specified command object does not have a {@link Command},  {@link Option} or {@link Parameters} annotation
 * @throws ExecutionException if the Callable throws an exception
 * @return {@code null} if an error occurred while parsing the command line options, or if help was requested and printed. Otherwise returns the result of calling the Callable
 * @see #execute(String)
 * @since 3.0
 * @deprecated use {@link #execute(String)} and {@link #getExecutionResult()} instead
 */
@Deprecated public static <C extends Callable<T>,T>T call(C callable,String... args){
  CommandLine cmd=new CommandLine(callable);
  List<Object> results=cmd.parseWithHandler(new RunLast(),args);
  return firstElement(results);
}
