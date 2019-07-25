/** 
 * Method that can be called on Object nodes, to access a property that has Object value; or if no such property exists, to create, add and return such Object node. If the node method is called on is not Object node, or if property exists and has value that is not Object node, {@link UnsupportedOperationException} is thrown
 */
public ObjectNode with(String propertyName){
  throw new UnsupportedOperationException("JsonNode not of type ObjectNode (but " + getClass().getName() + "), cannot call with() on it");
}
