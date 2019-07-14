/** 
 * ??from??
 */
public void clean(){
  for (  List<Vertex> vertexList : vertexes) {
    for (    Vertex vertex : vertexList) {
      vertex.from=null;
    }
  }
}
