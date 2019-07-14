/** 
 * ?????????
 * @param wordList
 */
public void learn(List<Word> wordList){
  LinkedList<char[]> sentence=new LinkedList<char[]>();
  for (  IWord iWord : wordList) {
    String word=iWord.getValue();
    if (word.length() == 1) {
      sentence.add(new char[]{word.charAt(0),'s'});
    }
 else {
      sentence.add(new char[]{word.charAt(0),'b'});
      for (int i=1; i < word.length() - 1; ++i) {
        sentence.add(new char[]{word.charAt(i),'m'});
      }
      sentence.add(new char[]{word.charAt(word.length() - 1),'e'});
    }
  }
  char[][] now=new char[3][];
  now[1]=bos;
  now[2]=bos;
  tf.add(1,bos,bos);
  tf.add(2,bos);
  for (  char[] i : sentence) {
    System.arraycopy(now,1,now,0,2);
    now[2]=i;
    tf.add(1,i);
    tf.add(1,now[1],now[2]);
    tf.add(1,now);
  }
}
