/** 
 * Delegates to  {@link #run(Runnable,PrintStream,PrintStream,Help.Ansi,String)} with {@code System.err} for diagnostic error messages.
 * @param runnable the command to run when {@linkplain #parseArgs(String) parsing} succeeds.
 * @param out the printStream to print the usage help message to when the user requested help
 * @param ansi whether the usage message should include ANSI escape codes or not
 * @param args the command line arguments to parse
 * @param < R > the annotated object must implement Runnable
 * @throws InitializationException if the specified command object does not have a {@link Command},  {@link Option} or {@link Parameters} annotation
 * @throws ExecutionException if the Runnable throws an exception
 * @deprecated use {@link #execute(String)} instead
 * @see RunLast
 */
@Deprecated public static <R extends Runnable>void run(R runnable,PrintStream out,Help.Ansi ansi,String... args){
  run(runnable,out,System.err,ansi,args);
}
