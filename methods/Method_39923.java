@Override public void doTag() throws JspException {
  PageContext pageContext=(PageContext)getJspContext();
  TagUtil.setScopeAttribute(name,value,scope,pageContext);
}
