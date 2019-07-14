/** 
 * ?? PartOfSpeechTagDictionary ??????????????????
 * @return
 */
public Sentence translateCompoundWordLabels(){
  for (  IWord word : wordList) {
    if (word instanceof CompoundWord)     word.setLabel(PartOfSpeechTagDictionary.translate(word.getLabel()));
  }
  return this;
}
