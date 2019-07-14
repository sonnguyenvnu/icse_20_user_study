/** 
 * ??????
 * @param path ?????????????????????????????com.hankcs.hanlp.corpus.dictionary.TFDictionary#combine(com.hankcs.hanlp.corpus.dictionary.TFDictionary, int, boolean)
 * @return ?????
 */
public static int combine(String... path){
  TFDictionary dictionaryMain=new TFDictionary();
  dictionaryMain.load(path[0]);
  int preSize=dictionaryMain.trie.size();
  for (int i=1; i < path.length; ++i) {
    TFDictionary dictionary=new TFDictionary();
    dictionary.load(path[i]);
    dictionaryMain.combine(dictionary,1,true);
  }
  try {
    BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(IOUtil.newOutputStream(path[0]),"UTF-8"));
    for (    Map.Entry<String,TermFrequency> entry : dictionaryMain.trie.entrySet()) {
      bw.write(entry.getKey());
      bw.write(' ');
      bw.write(String.valueOf(entry.getValue().getValue()));
      bw.newLine();
    }
    bw.close();
  }
 catch (  Exception e) {
    e.printStackTrace();
    return -1;
  }
  return dictionaryMain.trie.size() - preSize;
}
