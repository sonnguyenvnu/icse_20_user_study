/** 
 * Delegates to  {@link #usage(PrintStream,Help.ColorScheme)} with the {@linkplain Help#defaultColorScheme(CommandLine.Help.Ansi) default color scheme}.
 * @param out the printStream to print to
 * @param ansi whether the usage message should include ANSI escape codes or not
 * @see #usage(PrintStream,Help.ColorScheme)
 */
public void usage(PrintStream out,Help.Ansi ansi){
  usage(out,Help.defaultColorScheme(ansi));
}
