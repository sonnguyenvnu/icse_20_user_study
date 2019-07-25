/** 
 * Equivalent to  {@code new CommandLine(callableClass, factory).execute(args)}, except for the return value.
 * @param callableClass class of the command to call when {@linkplain #parseArgs(String) parsing} succeeds.
 * @param factory the factory responsible for instantiating the specified callable class and potentially inject other components
 * @param args the command line arguments to parse
 * @param < C > the annotated class must implement Callable
 * @param < T > the return type of the most specific command (must implement {@code Callable})
 * @throws InitializationException if the specified class cannot be instantiated by the factory, or does not have a {@link Command},  {@link Option} or {@link Parameters} annotation
 * @throws ExecutionException if the Callable throws an exception
 * @return {@code null} if an error occurred while parsing the command line options, or if help was requested and printed. Otherwise returns the result of calling the Callable
 * @see #execute(String)
 * @since 3.2
 * @deprecated use {@link #execute(String)} and {@link #getExecutionResult()} instead
 */
@Deprecated public static <C extends Callable<T>,T>T call(Class<C> callableClass,IFactory factory,String... args){
  CommandLine cmd=new CommandLine(callableClass,factory);
  List<Object> results=cmd.parseWithHandler(new RunLast(),args);
  return firstElement(results);
}
