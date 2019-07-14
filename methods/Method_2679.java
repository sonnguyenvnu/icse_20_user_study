public Searcher getSearcher(String text,int offset){
  return new Searcher(offset,text.toCharArray());
}
