/** 
 * Invokes tag body to provided writer.
 */
public static void invokeBody(final JspFragment body,final Writer writer) throws JspException {
  if (body == null) {
    return;
  }
  try {
    body.invoke(writer);
  }
 catch (  IOException ioex) {
    throw new JspException("Tag body failed",ioex);
  }
}
