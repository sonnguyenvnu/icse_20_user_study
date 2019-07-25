@Override public Iterator<SearchHit> iterator(){
  return Arrays.stream(getHits()).iterator();
}
