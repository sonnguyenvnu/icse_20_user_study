/** 
 * Gets the command-line options.
 * @param applicationArguments the Spring Application arguments
 * @return the Spark Shell options
 */
@Bean public SparkShellOptions parameters(@Nonnull final ApplicationArguments applicationArguments){
  final SparkShellOptions parameters=new SparkShellOptions();
  new JCommander(parameters,applicationArguments.getSourceArgs());
  return parameters;
}
