/** 
 * Method for accessing all value nodes of this Node, iff this node is a JSON Array or Object node. In case of Object node, field names (keys) are not included, only values. For other types of nodes, returns empty iterator.
 */
public Iterator<JsonNode> elements(){
  return ClassUtil.emptyIterator();
}
