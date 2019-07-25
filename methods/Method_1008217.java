@Override public Iterator<TermVectorsRequest> iterator(){
  return Collections.unmodifiableCollection(requests).iterator();
}
