/** 
 * Get a list of the names for all of the attributes for this node.
 * @webref xml:method
 * @brief Returns a list of names of all attributes as an array
 */
public String[] listAttributes(){
  NamedNodeMap nnm=node.getAttributes();
  String[] outgoing=new String[nnm.getLength()];
  for (int i=0; i < outgoing.length; i++) {
    outgoing[i]=nnm.item(i).getNodeName();
  }
  return outgoing;
}
