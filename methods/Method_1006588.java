/** 
 * Similar to  {@link #usage(PrintStream,Help.ColorScheme)}, but with the specified  {@code PrintWriter} instead of a {@code PrintStream}.
 * @since 3.0 
 */
public void usage(PrintWriter writer,Help.ColorScheme colorScheme){
  writer.print(usage(new StringBuilder(),getHelpFactory().create(getCommandSpec(),colorScheme)));
  writer.flush();
}
