/** 
 * Sets javac's  {@code -XDcompilePolicy} flag to ensure that all classes in a file are attributedbefore any of them are lowered. Error Prone depends on this behavior when analyzing files that contain multiple top-level classes.
 */
private static ImmutableList<String> setCompilePolicyToByFile(ImmutableList<String> args){
  for (  String arg : args) {
    if (arg.startsWith("-XDcompilePolicy")) {
      String value=arg.substring(arg.indexOf('=') + 1);
      checkCompilePolicy(value);
      return args;
    }
  }
  return ImmutableList.<String>builder().addAll(args).add("-XDcompilePolicy=byfile").build();
}
