/** 
 * ?????Hit?????DictSegment???????
 * @return Hit
 */
public Hit matchWithHit(char[] charArray,int currentIndex,Hit matchedHit){
  DictSegment ds=matchedHit.getMatchedDictSegment();
  return ds.match(charArray,currentIndex,1,matchedHit);
}
