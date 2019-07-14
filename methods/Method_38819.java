/** 
 * Sets the HTML contents of each element in the set of matched elements.
 */
public Jerry html(String html){
  if (html == null) {
    html=StringPool.EMPTY;
  }
  final Document doc=builder.parse(html);
  if (nodes.length == 0) {
    return this;
  }
  for (  Node node : nodes) {
    node.removeAllChilds();
    Document workingDoc=doc.clone();
    node.addChild(workingDoc.getChildNodes());
  }
  return this;
}
