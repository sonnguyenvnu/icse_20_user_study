/** 
 * Returns the next object if the specified name of the node fits to the next node, or  {@code null} if the node does not exist. In the last case the iterator will<i>not</i> increase its iteration counter.
 * @param nodeName the name of the node to be read next (must neither be null, nor empty)
 * @param required true if the occurrence of the node has to be ensured
 * @return the next object if the specified name of the node fits to the next node,otherwise null
 * @throws ConversionException if the specified node could not be found in the next nodehowever it was specified as required
 */
public Object next(String nodeName,boolean required) throws ConversionException {
  if (hasNext()) {
    Object nextNode=next();
    if (nextNode instanceof NodeName) {
      if (nodeName.equals(((NodeName)nextNode).getNodeName())) {
        return nextNode;
      }
    }
    this.index--;
  }
  if (required) {
    throw new ConversionException("The node '" + nodeName + "' is missing!");
  }
  return null;
}
