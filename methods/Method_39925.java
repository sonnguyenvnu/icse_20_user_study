/** 
 * Invokes tag body.
 */
public static void invokeBody(final JspFragment body) throws JspException {
  if (body == null) {
    return;
  }
  try {
    body.invoke(null);
  }
 catch (  IOException ioex) {
    throw new JspException("Tag body failed",ioex);
  }
}
