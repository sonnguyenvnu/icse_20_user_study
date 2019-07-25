/** 
 * Method that allows checking whether this node is JSON Array node and contains a value for specified index If this is the case (including case of specified indexing having null as value), returns true; otherwise returns false. <p> Note: array element indexes are 0-based. <p> This method is equivalent to: <pre> node.get(index) != null </pre> <p> NOTE: this method will return <code>true</code> for explicitly added null values.
 * @param index Index to check
 * @return True if this node is a JSON Object node, and has a propertyentry with specified name (with any value, including null value)
 */
public boolean has(int index){
  return get(index) != null;
}
