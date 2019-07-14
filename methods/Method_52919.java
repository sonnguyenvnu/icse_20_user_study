/** 
 * Creates a string that formats the template filename with line number and column of the given Directive. We use this routine to provide a cosistent format for displaying file errors.
 */
public static String formatFileString(final Directive directive){
  return formatFileString(directive.getTemplateName(),directive.getLine(),directive.getColumn());
}
