/** 
 * ?compute
 * @param sentenceList
 */
public void learn(List<Sentence> sentenceList){
  List<List<IWord>> s=new ArrayList<List<IWord>>(sentenceList.size());
  for (  Sentence sentence : sentenceList) {
    s.add(sentence.wordList);
  }
  compute(s);
}
