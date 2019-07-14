public static void normalize(Sentence sentence){
  for (  IWord word : sentence) {
    if (word instanceof CompoundWord) {
      for (      Word w : ((CompoundWord)word).innerList) {
        w.value=convert(w.value);
      }
    }
 else     word.setValue(word.getValue());
  }
}
