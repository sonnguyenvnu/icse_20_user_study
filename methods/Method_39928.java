/** 
 * Sets scope attribute.
 */
public static void setScopeAttribute(final String name,final Object value,final String scope,final PageContext pageContext) throws JspException {
  try {
    ServletUtil.setScopeAttribute(name,value,scope,pageContext);
  }
 catch (  UncheckedException uex) {
    throw new JspException(uex);
  }
}
