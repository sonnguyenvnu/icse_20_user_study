/** 
 * ???????
 * @param vertexList
 */
public void addAll(List<Vertex> vertexList){
  int i=0;
  for (  Vertex vertex : vertexList) {
    add(i,vertex);
    i+=vertex.realWord.length();
  }
}
