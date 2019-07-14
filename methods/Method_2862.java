/** 
 * ???ns???????
 * @param wordList
 */
public static void compileWithoutNT(List<IWord> wordList){
  for (  IWord word : wordList) {
    if (word.getLabel().startsWith("nt"))     continue;
    word.setValue(PosTagCompiler.compile(word.getLabel(),word.getValue()));
  }
}
