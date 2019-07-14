/** 
 * ???ns???????
 * @param wordList
 */
public static void compileWithoutNS(List<IWord> wordList){
  for (  IWord word : wordList) {
    if (word.getLabel().startsWith("ns"))     continue;
    word.setValue(PosTagCompiler.compile(word.getLabel(),word.getValue()));
  }
}
