/** 
 * @param pathData The string representing a path, the same as "d" string in svg file.
 * @return an array of the PathDataNode.
 */
@Nullable public static PathDataNode[] createNodesFromPathData(@Nullable String pathData){
  if (pathData == null) {
    return null;
  }
  int start=0;
  int end=1;
  ArrayList<PathDataNode> list=new ArrayList<PathDataNode>();
  while (end < pathData.length()) {
    end=nextStart(pathData,end);
    String s=pathData.substring(start,end).trim();
    if (s.length() > 0) {
      float[] val=getFloats(s);
      addNode(list,s.charAt(0),val);
    }
    start=end;
    end++;
  }
  if ((end - start) == 1 && start < pathData.length()) {
    addNode(list,pathData.charAt(start),new float[0]);
  }
  return list.toArray(new PathDataNode[list.size()]);
}
