/** 
 * ???query??????????
 * @param query
 * @param start
 * @return
 */
public List<int[]> commonPrefixSearch(String query,int start){
  List<int[]> ret=new ArrayList<int[]>(5);
  if ((query == null) || (start >= query.length())) {
    return ret;
  }
  int curState=1;
  for (int i=start; i < query.length(); i++) {
    int[] res=transferValues(curState,query.charAt(i));
    if (res[0] == -1) {
      break;
    }
    curState=res[0];
    if (res[1] != -1) {
      ret.add(new int[]{i - start + 1,res[1]});
    }
  }
  return ret;
}
