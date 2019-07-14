/** 
 * Performs smart form population.
 */
@Override public int doAfterBody() throws JspException {
  BodyContent body=getBodyContent();
  JspWriter out=body.getEnclosingWriter();
  String bodytext=populateForm(body.getString(),name -> value(name,pageContext));
  try {
    out.print(bodytext);
  }
 catch (  IOException ioex) {
    throw new JspException(ioex);
  }
  return SKIP_BODY;
}
