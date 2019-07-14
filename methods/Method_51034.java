/** 
 * @deprecated The equivalence between toString and a node's name could be broken as soon as release 7.0.0.Use getXPathNodeName for that purpose. The use for debugging purposes is not deprecated.
 */
@Deprecated @Override public String toString(){
  return getXPathNodeName();
}
