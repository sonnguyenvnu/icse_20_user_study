/** 
 * ?????
 * @return ??
 */
public Graph toGraph(){
  Graph graph=new Graph(getVertexesLineFirst());
  for (int row=0; row < vertexes.length - 1; ++row) {
    List<Vertex> vertexListFrom=vertexes[row];
    for (    Vertex from : vertexListFrom) {
      assert from.realWord.length() > 0 : "??????????";
      int toIndex=row + from.realWord.length();
      for (      Vertex to : vertexes[toIndex]) {
        graph.connect(from.index,to.index,MathUtility.calculateWeight(from,to));
      }
    }
  }
  return graph;
}
