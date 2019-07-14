/** 
 * Returns the keys that begins with the given key and its corresponding values. The first of the returned pair represents the length of the found key.
 * @param key
 * @param offset
 * @param maxResults
 * @return found keys and values
 */
public List<Pair<Integer,Integer>> commonPrefixSearch(byte[] key,int offset,int maxResults){
  ArrayList<Pair<Integer,Integer>> result=new ArrayList<Pair<Integer,Integer>>();
  int unit=_array[0];
  int nodePos=0;
  nodePos^=((unit >>> 10) << ((unit & (1 << 9)) >>> 6));
  for (int i=offset; i < key.length; ++i) {
    byte b=key[i];
    nodePos^=(b & 0xff);
    unit=_array[nodePos];
    if ((unit & ((1 << 31) | 0xFF)) != (b & 0xff)) {
      return result;
    }
    nodePos^=((unit >>> 10) << ((unit & (1 << 9)) >>> 6));
    if (((unit >>> 8) & 1) == 1) {
      if (result.size() < maxResults) {
        result.add(new Pair<Integer,Integer>(i + 1,_array[nodePos] & ((1 << 31) - 1)));
      }
    }
  }
  return result;
}
