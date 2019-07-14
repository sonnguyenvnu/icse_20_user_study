@Override protected void convertCorpus(Sentence sentence,BufferedWriter bw) throws IOException {
  for (  Word w : sentence.toSimpleWordList()) {
    String word=CharTable.convert(w.value);
    if (word.length() == 1) {
      bw.write(word);
      bw.write('\t');
      bw.write('S');
      bw.write('\n');
    }
 else {
      bw.write(word.charAt(0));
      bw.write('\t');
      bw.write('B');
      bw.write('\n');
      for (int i=1; i < word.length() - 1; ++i) {
        bw.write(word.charAt(i));
        bw.write('\t');
        bw.write('M');
        bw.write('\n');
      }
      bw.write(word.charAt(word.length() - 1));
      bw.write('\t');
      bw.write('E');
      bw.write('\n');
    }
  }
}
