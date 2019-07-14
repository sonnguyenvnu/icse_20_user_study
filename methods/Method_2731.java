/** 
 * ??NGram???????
 * @param path
 * @return
 */
public boolean saveTxtTo(String path){
  saveNGramToTxt(path + ".ngram.txt");
  saveTransformMatrixToTxt(path + ".tr.txt");
  return true;
}
