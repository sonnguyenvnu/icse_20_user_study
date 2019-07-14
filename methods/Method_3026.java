@Override protected Edge makeEdge(Node[] nodeArray,int from,int to){
  return model.getEdge(nodeArray[from],nodeArray[to]);
}
