/** 
 * Determines whether any of the matched elements are assigned the given class.
 */
public boolean hasClass(final String... classNames){
  if (nodes.length == 0) {
    return false;
  }
  for (  Node node : nodes) {
    String attrClass=node.getAttribute("class");
    Set<String> classes=createPropertiesSet(attrClass,' ');
    for (    String className : classNames) {
      if (classes.contains(className)) {
        return true;
      }
    }
  }
  return false;
}
