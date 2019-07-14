/** 
 * ????????????
 * @param line
 * @param vertex
 */
public void push(int line,Vertex vertex){
  Iterator<Vertex> iterator=vertexes[line].iterator();
  while (iterator.hasNext()) {
    if (iterator.next().realWord.length() == vertex.realWord.length()) {
      iterator.remove();
      --size;
      break;
    }
  }
  vertexes[line].add(vertex);
  ++size;
}
