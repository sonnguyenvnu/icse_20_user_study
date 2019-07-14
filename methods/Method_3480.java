/** 
 * ????????????????
 * @param path
 * @return
 */
public static String parseResult(List<Vertex> path){
  if (path.size() < 2) {
    throw new RuntimeException("???????2:" + path);
  }
  StringBuffer sb=new StringBuffer();
  for (int i=1; i < path.size() - 1; ++i) {
    Vertex v=path.get(i);
    sb.append(v.getRealWord() + " ");
  }
  return sb.toString();
}
