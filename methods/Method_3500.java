/** 
 * ???????????
 * @param line
 * @return
 */
public Vertex getFirst(int line){
  Iterator<Vertex> iterator=vertexes[line].iterator();
  if (iterator.hasNext())   return iterator.next();
  return null;
}
