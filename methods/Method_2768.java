/** 
 * ?? PartOfSpeechTagDictionary ???????????????
 * @return
 */
public Sentence translateLabels(){
  for (  IWord word : wordList) {
    word.setLabel(PartOfSpeechTagDictionary.translate(word.getLabel()));
    if (word instanceof CompoundWord) {
      for (      Word child : ((CompoundWord)word).innerList) {
        child.setLabel(PartOfSpeechTagDictionary.translate(child.getLabel()));
      }
    }
  }
  return this;
}
