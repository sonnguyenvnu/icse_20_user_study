/** 
 * ?????????????
 * @param cur ??
 * @param wordNetAll ??
 * @param line ???????
 * @param length ????????
 */
private static void removeFromWordNet(Vertex cur,WordNet wordNetAll,int line,int length){
  LinkedList<Vertex>[] vertexes=wordNetAll.getVertexes();
  for (  Vertex vertex : vertexes[line + length]) {
    if (vertex.from == cur)     vertex.from=null;
  }
  ListIterator<Vertex> iterator=vertexes[line + length - cur.realWord.length()].listIterator();
  while (iterator.hasNext()) {
    Vertex vertex=iterator.next();
    if (vertex == cur)     iterator.remove();
  }
}
