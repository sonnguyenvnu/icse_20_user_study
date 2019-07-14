/** 
 * ????
 * @param key
 * @param offset
 * @param maxResults
 * @return
 */
public ArrayList<Pair<String,V>> commonPrefixSearch(String key,int offset,int maxResults){
  byte[] keyBytes=key.getBytes(utf8);
  List<Pair<Integer,Integer>> pairList=commonPrefixSearch(keyBytes,offset,maxResults);
  ArrayList<Pair<String,V>> resultList=new ArrayList<Pair<String,V>>(pairList.size());
  for (  Pair<Integer,Integer> pair : pairList) {
    resultList.add(new Pair<String,V>(new String(keyBytes,0,pair.first),valueArray[pair.second]));
  }
  return resultList;
}
