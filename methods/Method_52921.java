/** 
 * Simply creates a string that formats the template filename with line number and column. We use this routine to provide a cosistent format for displaying file errors.
 * @param template File name of template, can be null
 * @param linenum Line number within the file
 * @param colnum Column number withing the file at linenum
 */
public static String formatFileString(String template,final int linenum,final int colnum){
  if (template == null || "".equals(template)) {
    template="<unknown template>";
  }
  return template + "[line " + linenum + ", column " + colnum + "]";
}
