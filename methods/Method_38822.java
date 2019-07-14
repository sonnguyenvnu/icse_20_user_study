/** 
 * Wraps an HTML structure around each element in the set of matched elements. Returns the original set of elements for chaining purposes.
 */
public Jerry wrap(String html){
  if (html == null) {
    html=StringPool.EMPTY;
  }
  final Document doc=builder.parse(html);
  if (nodes.length == 0) {
    return this;
  }
  for (  Node node : nodes) {
    Document workingDoc=doc.clone();
    Node inmostNode=workingDoc;
    while (inmostNode.hasChildNodes()) {
      inmostNode=inmostNode.getFirstChild();
    }
    Node parent=node.getParentNode();
    int index=node.getSiblingIndex();
    inmostNode.addChild(node);
    parent.insertChild(workingDoc.getFirstChild(),index);
  }
  return this;
}
