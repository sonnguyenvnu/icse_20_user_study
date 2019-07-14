/** 
 * ??????
 * @param a ????
 * @param b ????
 * @return ????@?????????
 */
public static int getBiFrequency(String a,String b){
  int idA=CoreDictionary.trie.exactMatchSearch(a);
  if (idA == -1) {
    return 0;
  }
  int idB=CoreDictionary.trie.exactMatchSearch(b);
  if (idB == -1) {
    return 0;
  }
  int index=binarySearch(pair,start[idA],start[idA + 1] - start[idA],idB);
  if (index < 0)   return 0;
  index<<=1;
  return pair[index + 1];
}
