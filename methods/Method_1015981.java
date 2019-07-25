@Override public A pop(){
  Map.Entry<Long,A> entry=this.map.pollLastEntry();
  return entry == null ? null : entry.getValue();
}
