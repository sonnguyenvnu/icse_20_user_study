/** 
 * Throws InvalidCommandLineOptionException if the  {@code -XDcompilePolicy} flag is set to anunsupported value
 */
static void checkCompilePolicy(@Nullable String compilePolicy){
  if (compilePolicy == null) {
    throw new InvalidCommandLineOptionException("The default compilation policy (by-todo) is not supported by Error Prone," + " pass -XDcompilePolicy=byfile instead");
  }
switch (compilePolicy) {
case "byfile":
case "simple":
    break;
default :
  throw new InvalidCommandLineOptionException(String.format("-XDcompilePolicy=%s is not supported by Error Prone," + " pass -XDcompilePolicy=byfile instead",compilePolicy));
}
}
