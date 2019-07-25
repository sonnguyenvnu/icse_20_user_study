@Override public Tokenizer create(){
  JapaneseTokenizer t=new JapaneseTokenizer(userDictionary,discartPunctuation,mode);
  int nBestCost=this.nBestCost;
  if (nBestExamples != null) {
    nBestCost=Math.max(nBestCost,t.calcNBestCost(nBestExamples));
  }
  t.setNBestCost(nBestCost);
  return t;
}
