/** 
 * @see ADictionary#add(int,IWord) 
 */
@Override public IWord add(int t,IWord word){
  if (t >= 0 && t < ILexicon.T_LEN) {
    if (dics[t].containsKey(word.getValue())) {
      return dics[t].get(word.getValue());
    }
    dics[t].put(word.getValue(),word);
    return word;
  }
  return null;
}
