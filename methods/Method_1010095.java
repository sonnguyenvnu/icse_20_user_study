private static void restore(Element e,PositionInfo pi){
  final String id=e.getAttributeValue(ATTR_NODE_ID);
  if (id != null) {
    pi.setNodeId(id);
  }
  final String[] at=e.getAttributeValue(ATTR_AT).split(",");
  pi.setStartLine(Integer.parseInt(at[0]));
  pi.setStartPosition(Integer.parseInt(at[1]));
  pi.setEndLine(Integer.parseInt(at[2]));
  pi.setEndPosition(Integer.parseInt(at[3]));
}
