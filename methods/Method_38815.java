/** 
 * Sets one or more CSS properties for the set of matched elements.
 */
public Jerry css(final String... css){
  if (nodes.length == 0) {
    return this;
  }
  for (  Node node : nodes) {
    String styleAttrValue=node.getAttribute("style");
    Map<String,String> styles=createPropertiesMap(styleAttrValue,';',':');
    for (int i=0; i < css.length; i+=2) {
      String propertyName=css[i];
      propertyName=Format.fromCamelCase(propertyName,'-');
      String value=css[i + 1];
      if (value.length() == 0) {
        styles.remove(propertyName);
      }
 else {
        styles.put(propertyName,value);
      }
    }
    styleAttrValue=generateAttributeValue(styles,';',':');
    node.setAttribute("style",styleAttrValue);
  }
  return this;
}
