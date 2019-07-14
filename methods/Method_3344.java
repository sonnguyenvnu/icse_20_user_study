/** 
 * ??????Segment??
 * @return
 */
public Segment toSegment(){
  return new Segment(){
    @Override protected List<Term> segSentence(    char[] sentence){
      List<String> wordList=segment(new String(sentence));
      List<Term> termList=new LinkedList<Term>();
      for (      String word : wordList) {
        termList.add(new Term(word,null));
      }
      return termList;
    }
  }
.enableCustomDictionary(false);
}
