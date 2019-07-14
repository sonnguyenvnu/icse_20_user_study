/** 
 * ????
 * @param reader ???
 * @param size   ?????????
 * @return ??????
 */
public List<WordInfo> discover(BufferedReader reader,int size) throws IOException {
  String doc;
  Map<String,WordInfo> word_cands=new TreeMap<String,WordInfo>();
  int totalLength=0;
  Pattern delimiter=Pattern.compile("[\\s\\d,.<>/?:;'\"\\[\\]{}()\\|~!@#$%^&*\\-_=+????????“”‘’??????…??—??]+");
  while ((doc=reader.readLine()) != null) {
    doc=delimiter.matcher(doc).replaceAll("\0");
    int docLength=doc.length();
    for (int i=0; i < docLength; ++i) {
      int end=Math.min(i + 1 + max_word_len,docLength + 1);
      for (int j=i + 1; j < end; ++j) {
        String word=doc.substring(i,j);
        if (word.indexOf('\0') >= 0)         continue;
        WordInfo info=word_cands.get(word);
        if (info == null) {
          info=new WordInfo(word);
          word_cands.put(word,info);
        }
        info.update(i == 0 ? '\0' : doc.charAt(i - 1),j < docLength ? doc.charAt(j) : '\0');
      }
    }
    totalLength+=docLength;
  }
  for (  WordInfo info : word_cands.values()) {
    info.computeProbabilityEntropy(totalLength);
  }
  for (  WordInfo info : word_cands.values()) {
    info.computeAggregation(word_cands);
  }
  List<WordInfo> wordInfoList=new LinkedList<WordInfo>(word_cands.values());
  ListIterator<WordInfo> listIterator=wordInfoList.listIterator();
  while (listIterator.hasNext()) {
    WordInfo info=listIterator.next();
    if (info.text.trim().length() < 2 || info.p < min_freq || info.entropy < min_entropy || info.aggregation < min_aggregation || (filter && LexiconUtility.getFrequency(info.text) > 0)) {
      listIterator.remove();
    }
  }
  MaxHeap<WordInfo> topN=new MaxHeap<WordInfo>(size,new Comparator<WordInfo>(){
    public int compare(    WordInfo o1,    WordInfo o2){
      return Float.compare(o1.p,o2.p);
    }
  }
);
  topN.addAll(wordInfoList);
  return topN.toList();
}
