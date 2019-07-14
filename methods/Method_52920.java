/** 
 * Creates a string that formats the template filename with line number and column of the given Node. We use this routine to provide a cosistent format for displaying file errors.
 */
public static String formatFileString(final AbstractVmNode node){
  return formatFileString(node.getTemplateName(),node.getLine(),node.getColumn());
}
