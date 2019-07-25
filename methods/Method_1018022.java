/** 
 * Gets the command-line options.
 * @param applicationArguments the Spring Application arguments
 * @return the Spark Shell options
 */
@Bean @Primary public SparkShellOptions parameters(){
  final SparkShellOptions parameters=new SparkShellOptions();
  return parameters;
}
