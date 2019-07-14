private void reportUnnecessaryModifiers(Object data,Node node,Set<Modifier> unnecessaryModifiers,String explanation){
  if (unnecessaryModifiers.isEmpty()) {
    return;
  }
  super.addViolation(data,node,new String[]{formatUnnecessaryModifiers(unnecessaryModifiers),getPrintableNodeKind(node),getNodeName(node),explanation.isEmpty() ? "" : ": " + explanation});
}
