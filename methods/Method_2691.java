public static boolean makeModel(String corpusLoadPath,String modelSavePath) throws IOException {
  BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(IOUtil.newOutputStream(modelSavePath)));
  LinkedList<CoNLLSentence> sentenceList=CoNLLLoader.loadSentenceList(corpusLoadPath);
  int id=1;
  for (  CoNLLSentence sentence : sentenceList) {
    System.out.printf("%d / %d...",id++,sentenceList.size());
    String[][] edgeArray=sentence.getEdgeArray();
    CoNLLWord[] word=sentence.getWordArrayWithRoot();
    for (int i=0; i < word.length; ++i) {
      for (int j=0; j < word.length; ++j) {
        if (i == j)         continue;
        List<String> contextList=new LinkedList<String>();
        contextList.addAll(generateSingleWordContext(word,i,"i"));
        contextList.addAll(generateSingleWordContext(word,j,"j"));
        contextList.addAll(generateUniContext(word,i,j));
        for (        String f : contextList) {
          bw.write(f);
          bw.write(' ');
        }
        bw.write("" + edgeArray[i][j]);
        bw.newLine();
      }
    }
    System.out.println("done.");
  }
  bw.close();
  return true;
}
