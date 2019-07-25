/** 
 * Equivalent to  {@code new CommandLine(command).usage(out, colorScheme)}. See  {@link #usage(PrintStream,Help.ColorScheme)} for details.
 * @param command the object annotated with {@link Command},  {@link Option} and {@link Parameters}
 * @param out the print stream to print the help message to
 * @param colorScheme the {@code ColorScheme} defining the styles for options, parameters and commands when ANSI is enabled
 * @throws IllegalArgumentException if the specified command object does not have a {@link Command},  {@link Option} or {@link Parameters} annotation
 */
public static void usage(Object command,PrintStream out,Help.ColorScheme colorScheme){
  toCommandLine(command,new DefaultFactory()).usage(out,colorScheme);
}
