/** 
 * ??????????????????length????????
 * @param length
 * @param size
 * @param extend
 * @return
 */
public List<String> extractSuffixByWords(int length,int size,boolean extend){
  TFDictionary suffixTreeSet=new TFDictionary();
  for (  String key : tfDictionary.keySet()) {
    List<Term> termList=StandardTokenizer.segment(key);
    if (termList.size() > length) {
      suffixTreeSet.add(combine(termList.subList(termList.size() - length,termList.size())));
      if (extend) {
        for (int l=1; l < length; ++l) {
          suffixTreeSet.add(combine(termList.subList(termList.size() - l,termList.size())));
        }
      }
    }
  }
  return extract(suffixTreeSet,size);
}
