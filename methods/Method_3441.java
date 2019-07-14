/** 
 * ??????????????????????
 * @param inputFolder ?????????????(?????????????????????)
 * @param outputFile  ?????CRF???????
 * @param begin       ????????
 * @param end
 * @throws IOException ??????IO??
 */
public static void convertPKUtoPOS(String inputFolder,String outputFile,final int begin,final int end) throws IOException {
  final BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile),"UTF-8"));
  CorpusLoader.walk(inputFolder,new CorpusLoader.Handler(){
    @Override public void handle(    Document document){
      ++doc;
      if (doc < begin || doc > end)       return;
      try {
        List<List<Word>> sentenceList=document.getSimpleSentenceList();
        if (sentenceList.size() == 0)         return;
        for (        List<Word> sentence : sentenceList) {
          if (sentence.size() == 0)           continue;
          int index=0;
          for (          IWord iWord : sentence) {
            bw.write(iWord.toString());
            if (++index != sentence.size()) {
              bw.write(' ');
            }
          }
          bw.newLine();
        }
      }
 catch (      IOException e) {
        e.printStackTrace();
      }
    }
  }
);
  bw.close();
}
