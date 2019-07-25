private static void serialize(PositionInfo pi,Element e){
  if (pi.getNodeId() != null) {
    e.setAttribute(ATTR_NODE_ID,pi.getNodeId());
  }
  e.setAttribute(ATTR_AT,String.format("%d,%d,%d,%d",pi.getStartLine(),pi.getStartPosition(),pi.getEndLine(),pi.getEndPosition()));
}
