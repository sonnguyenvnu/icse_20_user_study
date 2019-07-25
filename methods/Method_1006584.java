/** 
 * Delegates to  {@link #usage(PrintWriter,Help.ColorScheme)} with the {@linkplain #getColorScheme() configured} color scheme.
 * @param writer the PrintWriter to print to
 * @see #usage(PrintWriter,Help.ColorScheme)
 * @since 3.0 
 */
public void usage(PrintWriter writer){
  usage(writer,getColorScheme());
}
