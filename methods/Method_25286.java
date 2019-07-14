/** 
 * Given a list of command-line arguments, produce the corresponding  {@link ErrorProneOptions}instance.
 * @param args command-line arguments
 * @return an {@link ErrorProneOptions} instance encapsulating the given arguments
 * @throws InvalidCommandLineOptionException if an error-prone option is invalid
 */
public static ErrorProneOptions processArgs(String[] args){
  Preconditions.checkNotNull(args);
  return processArgs(Arrays.asList(args));
}
