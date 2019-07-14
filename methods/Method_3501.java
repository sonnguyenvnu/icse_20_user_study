/** 
 * ??????
 * @return Vertex[] ?????????????????
 */
private Vertex[] getVertexesLineFirst(){
  Vertex[] vertexes=new Vertex[size];
  int i=0;
  for (  List<Vertex> vertexList : this.vertexes) {
    for (    Vertex v : vertexList) {
      v.index=i;
      vertexes[i++]=v;
    }
  }
  return vertexes;
}
