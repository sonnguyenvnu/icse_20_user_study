public Searcher getSearcher(char[] text,int offset){
  return new Searcher(offset,text);
}
