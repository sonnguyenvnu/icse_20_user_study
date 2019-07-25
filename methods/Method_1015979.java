@Override public A pot(){
  Map.Entry<Long,A> entry=this.map.pollFirstEntry();
  return entry == null ? null : entry.getValue();
}
