/** 
 * ????????size?????????rank
 * @param content
 * @param size
 * @return
 */
public Map<String,Float> getTermAndRank(String content,int size){
  Map<String,Float> map=getTermAndRank(content);
  Map<String,Float> result=top(size,map);
  return result;
}
