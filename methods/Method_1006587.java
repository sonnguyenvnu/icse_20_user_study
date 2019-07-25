/** 
 * Prints a usage help message for the annotated command class to the specified  {@code PrintStream}. Delegates construction of the usage help message to the  {@link Help} inner class and is equivalent to:<pre> Help.ColorScheme colorScheme = Help.defaultColorScheme(Help.Ansi.AUTO); Help help = getHelpFactory().create(getCommandSpec(), colorScheme) StringBuilder sb = new StringBuilder(); for (String key : getHelpSectionKeys()) { IHelpSectionRenderer renderer = getHelpSectionMap().get(key); if (renderer != null) { sb.append(renderer.render(help)); } } out.print(sb); </pre> <p>Annotate your class with  {@link Command} to control many aspects of the usage help message, includingthe program name, text of section headings and section contents, and some aspects of the auto-generated sections of the usage help message. <p>To customize the auto-generated sections of the usage help message, like how option details are displayed, instantiate a  {@link Help} object and use a {@link Help.TextTable} with more of fewer columns, a custom{@linkplain Help.Layout layout}, and/or a custom option  {@linkplain Help.IOptionRenderer renderer}for ultimate control over which aspects of an Option or Field are displayed where.</p>
 * @param out the {@code PrintStream} to print the usage help message to
 * @param colorScheme the {@code ColorScheme} defining the styles for options, parameters and commands when ANSI is enabled
 * @see UsageMessageSpec
 */
public void usage(PrintStream out,Help.ColorScheme colorScheme){
  out.print(usage(new StringBuilder(),getHelpFactory().create(getCommandSpec(),colorScheme)));
  out.flush();
}
