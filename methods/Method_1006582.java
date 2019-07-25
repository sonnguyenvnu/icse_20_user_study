/** 
 * Equivalent to  {@code new CommandLine(command).usage(out, ansi)}. See  {@link #usage(PrintStream,Help.Ansi)} for details.
 * @param command the object annotated with {@link Command},  {@link Option} and {@link Parameters}
 * @param out the print stream to print the help message to
 * @param ansi whether the usage message should contain ANSI escape codes or not
 * @throws IllegalArgumentException if the specified command object does not have a {@link Command},  {@link Option} or {@link Parameters} annotation
 */
public static void usage(Object command,PrintStream out,Help.Ansi ansi){
  toCommandLine(command,new DefaultFactory()).usage(out,ansi);
}
