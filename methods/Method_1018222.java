/** 
 * @see ADictionary#add(int,String,int,int,String[]) 
 */
@Override public IWord add(int t,String key,int fre,int type,String entity[]){
  if (t >= 0 && t < ILexicon.T_LEN) {
    if (dics[t].containsKey(key)) {
      return dics[t].get(key);
    }
    IWord word=new Word(key,fre,type,entity);
    dics[t].put(key,word);
    return word;
  }
  return null;
}
