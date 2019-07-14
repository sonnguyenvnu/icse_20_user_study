/** 
 * Validates argument index.
 */
public static void checkArgumentIndex(final MethodInfo methodInfo,final int argIndex){
  if ((argIndex < 1) || (argIndex > methodInfo.getArgumentsCount())) {
    throw new ProxettaException("Invalid argument index: " + argIndex);
  }
}
