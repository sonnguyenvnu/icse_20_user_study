/** 
 * ???????? n ???
 * @param n
 * @return
 */
public List<int[]> getNPaths(int n){
  List<int[]> result=new ArrayList<int[]>();
  n=Math.min(Predefine.MAX_SEGMENT_NUM,n);
  for (int i=0; i < N && result.size() < n; ++i) {
    List<int[]> pathList=getPaths(i);
    for (    int[] path : pathList) {
      if (result.size() == n)       break;
      result.add(path);
    }
  }
  return result;
}
