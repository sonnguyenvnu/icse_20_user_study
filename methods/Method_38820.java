/** 
 * Inserts content, specified by the parameter, to the end of each element in the set of matched elements.
 */
public Jerry append(String html){
  if (html == null) {
    html=StringPool.EMPTY;
  }
  final Document doc=builder.parse(html);
  if (nodes.length == 0) {
    return this;
  }
  for (  Node node : nodes) {
    Document workingDoc=doc.clone();
    node.addChild(workingDoc.getChildNodes());
  }
  return this;
}
