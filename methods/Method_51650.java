public void resetTo(List<DFAGraphMethod> newNodes,LineGetter lines){
  dfaCanvas.setCode(lines);
  nodes.clear();
  for (  DFAGraphMethod md : newNodes) {
    nodes.addElement(new ElementWrapper(md));
  }
  nodeList.setSelectedIndex(0);
  dfaCanvas.setMethod(newNodes.get(0));
  repaint();
}
