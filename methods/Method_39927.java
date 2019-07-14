/** 
 * Renders tag body to char array.
 */
public static char[] renderBody(final JspFragment body) throws JspException {
  FastCharArrayWriter writer=new FastCharArrayWriter();
  invokeBody(body,writer);
  return writer.toCharArray();
}
