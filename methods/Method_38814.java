/** 
 * Gets the value of a style property for the first element in the set of matched elements. Returns <code>null</code> if set is empty.
 */
public String css(String propertyName){
  if (nodes.length == 0) {
    return null;
  }
  propertyName=Format.fromCamelCase(propertyName,'-');
  String styleAttrValue=nodes[0].getAttribute("style");
  if (styleAttrValue == null) {
    return null;
  }
  Map<String,String> styles=createPropertiesMap(styleAttrValue,';',':');
  return styles.get(propertyName);
}
