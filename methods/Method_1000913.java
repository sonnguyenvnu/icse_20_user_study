/** 
 * Method that allows checking whether this node is JSON Object node and contains value for specified property. If this is the case (including properties with explicit null values), returns true; otherwise returns false. <p> This method is equivalent to: <pre> node.get(fieldName) != null </pre> (since return value of get() is node, not value node contains) <p> NOTE: when explicit <code>null</code> values are added, this method will return <code>true</code> for such properties.
 * @param fieldName Name of element to check
 * @return True if this node is a JSON Object node, and has a propertyentry with specified name (with any value, including null value)
 */
public boolean has(String fieldName){
  return get(fieldName) != null;
}
