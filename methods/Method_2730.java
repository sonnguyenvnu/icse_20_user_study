/** 
 * ?????????????????
 * @return
 */
static boolean makeCoreDictionary(String inPath,String outPath){
  final DictionaryMaker dictionaryMaker=new DictionaryMaker();
  final TreeSet<String> labelSet=new TreeSet<String>();
  CorpusLoader.walk(inPath,new CorpusLoader.Handler(){
    @Override public void handle(    Document document){
      for (      List<Word> sentence : document.getSimpleSentenceList(true)) {
        for (        Word word : sentence) {
          if (shouldInclude(word))           dictionaryMaker.add(word);
        }
      }
    }
    /** 
 * ??????????
 * @param word
 * @return
 */
    boolean shouldInclude(    Word word){
      if ("m".equals(word.label) || "mq".equals(word.label) || "w".equals(word.label) || "t".equals(word.label)) {
        if (!TextUtility.isAllChinese(word.value))         return false;
      }
 else       if ("nr".equals(word.label)) {
        return false;
      }
      return true;
    }
  }
);
  if (outPath != null)   return dictionaryMaker.saveTxtTo(outPath);
  return false;
}
