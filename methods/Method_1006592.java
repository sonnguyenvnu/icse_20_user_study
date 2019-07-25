/** 
 * Delegates to  {@link #call(Callable,PrintStream,PrintStream,Help.Ansi,String)} with {@code System.err} for diagnostic error messages.
 * @param callable the command to call when {@linkplain #parseArgs(String) parsing} succeeds.
 * @param out the printStream to print the usage help message to when the user requested help
 * @param ansi the ANSI style to use
 * @param args the command line arguments to parse
 * @param < C > the annotated object must implement Callable
 * @param < T > the return type of the most specific command (must implement {@code Callable})
 * @throws InitializationException if the specified command object does not have a {@link Command},  {@link Option} or {@link Parameters} annotation
 * @throws ExecutionException if the Callable throws an exception
 * @return {@code null} if an error occurred while parsing the command line options, or if help was requested and printed. Otherwise returns the result of calling the Callable
 * @deprecated use {@link #execute(String)} and {@link #getExecutionResult()} instead
 * @see RunLast
 */
@Deprecated public static <C extends Callable<T>,T>T call(C callable,PrintStream out,Help.Ansi ansi,String... args){
  return call(callable,out,System.err,ansi,args);
}
