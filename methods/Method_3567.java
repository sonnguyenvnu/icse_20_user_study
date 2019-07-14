private static List<Vertex> viterbi(WordNet wordNet){
  LinkedList<Vertex> nodes[]=wordNet.getVertexes();
  LinkedList<Vertex> vertexList=new LinkedList<Vertex>();
  for (  Vertex node : nodes[1]) {
    node.updateFrom(nodes[0].getFirst());
  }
  for (int i=1; i < nodes.length - 1; ++i) {
    LinkedList<Vertex> nodeArray=nodes[i];
    if (nodeArray == null)     continue;
    for (    Vertex node : nodeArray) {
      if (node.from == null)       continue;
      for (      Vertex to : nodes[i + node.realWord.length()]) {
        to.updateFrom(node);
      }
    }
  }
  Vertex from=nodes[nodes.length - 1].getFirst();
  while (from != null) {
    vertexList.addFirst(from);
    from=from.from;
  }
  return vertexList;
}
