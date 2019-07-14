@Override protected void convertCorpus(Sentence sentence,BufferedWriter bw) throws IOException {
  List<Word> simpleWordList=sentence.toSimpleWordList();
  List<String> wordList=new ArrayList<String>(simpleWordList.size());
  for (  Word word : simpleWordList) {
    wordList.add(word.value);
  }
  String[] words=wordList.toArray(new String[0]);
  Iterator<Word> iterator=simpleWordList.iterator();
  for (int i=0; i < words.length; i++) {
    String curWord=words[i];
    String[] cells=createCells(true);
    extractFeature(curWord,cells);
    cells[5]=iterator.next().label;
    for (int j=0; j < cells.length; j++) {
      bw.write(cells[j]);
      if (j != cells.length - 1)       bw.write('\t');
    }
    bw.newLine();
  }
}
