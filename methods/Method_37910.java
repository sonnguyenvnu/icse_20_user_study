/** 
 * Adds several arguments.
 */
public CommandLine args(final String... arguments){
  if (arguments != null && arguments.length > 0) {
    Collections.addAll(cmdLine,arguments);
  }
  return this;
}
