/** 
 * Renders attribute.
 */
protected void renderAttribute(final Node node,final Attribute attribute,final Appendable appendable) throws IOException {
  String name=resolveAttributeName(node,attribute);
  String value=attribute.getValue();
  appendable.append(name);
  if (value != null) {
    appendable.append('=');
    appendable.append('\"');
    appendable.append(HtmlEncoder.attributeDoubleQuoted(value));
    appendable.append('\"');
  }
}
