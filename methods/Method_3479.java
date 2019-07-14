/** 
 * ????????????????
 * @param path
 * @return
 */
public List<Vertex> parsePath(int[] path){
  List<Vertex> vertexList=new LinkedList<Vertex>();
  for (  int i : path) {
    vertexList.add(vertexes[i]);
  }
  return vertexList;
}
