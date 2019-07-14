public LongList copy(){
  LongList outgoing=new LongList(data);
  outgoing.count=count;
  return outgoing;
}
