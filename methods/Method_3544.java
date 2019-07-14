/** 
 * ??<br> ?????????
 * @param text ?????
 * @return ????
 */
public List<Term> seg(String text){
  char[] charArray=text.toCharArray();
  if (HanLP.Config.Normalization) {
    CharTable.normalization(charArray);
  }
  if (config.threadNumber > 1 && charArray.length > 10000) {
    List<String> sentenceList=SentencesUtil.toSentenceList(charArray);
    String[] sentenceArray=new String[sentenceList.size()];
    sentenceList.toArray(sentenceArray);
    List<Term>[] termListArray=new List[sentenceArray.length];
    final int per=sentenceArray.length / config.threadNumber;
    WorkThread[] threadArray=new WorkThread[config.threadNumber];
    for (int i=0; i < config.threadNumber - 1; ++i) {
      int from=i * per;
      threadArray[i]=new WorkThread(sentenceArray,termListArray,from,from + per);
      threadArray[i].start();
    }
    threadArray[config.threadNumber - 1]=new WorkThread(sentenceArray,termListArray,(config.threadNumber - 1) * per,sentenceArray.length);
    threadArray[config.threadNumber - 1].start();
    try {
      for (      WorkThread thread : threadArray) {
        thread.join();
      }
    }
 catch (    InterruptedException e) {
      logger.severe("???????" + TextUtility.exceptionToString(e));
      return Collections.emptyList();
    }
    List<Term> termList=new LinkedList<Term>();
    if (config.offset || config.indexMode > 0) {
      int sentenceOffset=0;
      for (int i=0; i < sentenceArray.length; ++i) {
        for (        Term term : termListArray[i]) {
          term.offset+=sentenceOffset;
          termList.add(term);
        }
        sentenceOffset+=sentenceArray[i].length();
      }
    }
 else {
      for (      List<Term> list : termListArray) {
        termList.addAll(list);
      }
    }
    return termList;
  }
  return segSentence(charArray);
}
