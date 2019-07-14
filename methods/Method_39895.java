/** 
 * Returns value of property/attribute. The following value sets are looked up: <ul> <li>page context attributes</li> <li>request attributes</li> <li>request parameters (multi-part request detected)</li> <li>session attributes</li> <li>context attributes</li> </ul>
 */
public static Object value(final PageContext pageContext,final String name){
  Object value=pageContext.getAttribute(name);
  if (value != null) {
    return value;
  }
  return value((HttpServletRequest)pageContext.getRequest(),name);
}
