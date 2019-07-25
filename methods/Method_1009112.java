public static void serialise(Node sourceNode,StringBuffer result,String indent){
switch (sourceNode.getNodeType()) {
case Node.DOCUMENT_NODE:
    NodeList nodes=sourceNode.getChildNodes();
  if (nodes != null) {
    for (int i=0; i < nodes.getLength(); i++) {
      serialise((Node)nodes.item(i),result,indent);
    }
  }
break;
case Node.ELEMENT_NODE:
result.append("\n" + indent + "<" + sourceNode.getNodeName());
NamedNodeMap atts=sourceNode.getAttributes();
for (int i=0; i < atts.getLength(); i++) {
Attr attr=(Attr)atts.item(i);
result.append(" " + attr.getName() + "=\"" + attr.getValue() + "\"");
}
result.append(">");
if (sourceNode.getChildNodes() == null || sourceNode.getChildNodes().getLength() == 0) {
result.append("</" + sourceNode.getNodeName() + ">");
}
 else {
NodeList children=sourceNode.getChildNodes();
boolean hasNonTextChild=false;
if (children != null) {
for (int i=0; i < children.getLength(); i++) {
if (children.item(i).getNodeType() != Node.TEXT_NODE) {
hasNonTextChild=true;
}
serialise((Node)children.item(i),result,indent + "  ");
}
}
if (hasNonTextChild) {
result.append("\n" + indent);
}
result.append("</" + sourceNode.getNodeName() + ">");
}
break;
case Node.TEXT_NODE:
result.append(sourceNode.getNodeValue());
break;
}
}
