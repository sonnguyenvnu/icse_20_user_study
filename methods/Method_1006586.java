/** 
 * Similar to  {@link #usage(PrintStream,Help.Ansi)} but with the specified {@code PrintWriter} instead of a {@code PrintStream}.
 * @since 3.0 
 */
public void usage(PrintWriter writer,Help.Ansi ansi){
  usage(writer,Help.defaultColorScheme(ansi));
}
