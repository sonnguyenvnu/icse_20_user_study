private static List<List<Word>> convertComplexWordToSimpleWord(List<List<IWord>> document){
  String nerTag[]=new String[]{"nr","ns","nt"};
  List<List<Word>> output=new ArrayList<List<Word>>(document.size());
  for (  List<IWord> sentence : document) {
    List<Word> s=new ArrayList<Word>(sentence.size());
    for (    IWord iWord : sentence) {
      if (iWord instanceof Word) {
        s.add((Word)iWord);
      }
 else       if (isNer(iWord,nerTag)) {
        s.add(new Word(iWord.getValue(),iWord.getLabel()));
      }
 else {
        for (        Word word : ((CompoundWord)iWord).innerList) {
          isNer(word,nerTag);
          s.add(word);
        }
      }
    }
    output.add(s);
  }
  return output;
}
