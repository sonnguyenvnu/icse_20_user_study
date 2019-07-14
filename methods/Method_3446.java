/** 
 * ?????? ???????NER??????
 * @param sentence
 * @param tagSet
 * @return
 */
public static List<String[]> convertSentenceToNER(Sentence sentence,NERTagSet tagSet){
  List<String[]> collector=new LinkedList<String[]>();
  Set<String> nerLabels=tagSet.nerLabels;
  for (  IWord word : sentence.wordList) {
    if (word instanceof CompoundWord) {
      List<Word> wordList=((CompoundWord)word).innerList;
      Word[] words=wordList.toArray(new Word[0]);
      if (nerLabels.contains(word.getLabel())) {
        collector.add(new String[]{words[0].value,words[0].label,tagSet.B_TAG_PREFIX + word.getLabel()});
        for (int i=1; i < words.length - 1; i++) {
          collector.add(new String[]{words[i].value,words[i].label,tagSet.M_TAG_PREFIX + word.getLabel()});
        }
        collector.add(new String[]{words[words.length - 1].value,words[words.length - 1].label,tagSet.E_TAG_PREFIX + word.getLabel()});
      }
 else {
        for (        Word w : words) {
          collector.add(new String[]{w.value,w.label,tagSet.O_TAG});
        }
      }
    }
 else {
      if (nerLabels.contains(word.getLabel())) {
        collector.add(new String[]{word.getValue(),word.getLabel(),tagSet.S_TAG});
      }
 else {
        collector.add(new String[]{word.getValue(),word.getLabel(),tagSet.O_TAG});
      }
    }
  }
  return collector;
}
