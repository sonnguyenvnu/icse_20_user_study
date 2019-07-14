/** 
 * ???????????
 * @param tagger ?????
 * @param corpus ???
 * @return Accuracy???
 */
public static float evaluate(POSTagger tagger,String corpus){
  int correct=0, total=0;
  IOUtil.LineIterator lineIterator=new IOUtil.LineIterator(corpus);
  for (  String line : lineIterator) {
    Sentence sentence=Sentence.create(line);
    if (sentence == null)     continue;
    String[][] wordTagArray=sentence.toWordTagArray();
    String[] prediction=tagger.tag(wordTagArray[0]);
    assert prediction.length == wordTagArray[1].length;
    total+=prediction.length;
    for (int i=0; i < prediction.length; i++) {
      if (prediction[i].equals(wordTagArray[1][i]))       ++correct;
    }
  }
  if (total == 0)   return 0;
  return correct / (float)total * 100;
}
