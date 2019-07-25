/** 
 * @param nodeIterator
 * @deprecated
 */
public static void log(NodeIterator nodeIterator){
  Node n=nodeIterator.nextNode();
  log.info(XmlUtils.w3CDomNodeToString(n));
}
