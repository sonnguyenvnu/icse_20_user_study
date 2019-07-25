/** 
 * Applies the provided argument values to all  {@literal @CmdLine} {@code Arg} fields discoveredon the classpath.
 * @param args Argument values to map, parse, validate, and apply.
 * @return {@code true} if the given {@code args} were successfully applied to their corresponding{@link Arg} fields.
 * @throws ArgScanException if there was a problem loading {@literal @CmdLine} argumentdefinitions
 * @throws IllegalArgumentException If the arguments provided are invalid based on the declaredarguments found.
 */
public boolean parse(Iterable<String> args) throws ArgScanException, IllegalArgumentException {
  return parse(ArgFilters.SELECT_ALL,ImmutableList.copyOf(args));
}
