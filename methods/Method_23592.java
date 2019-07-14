public DoubleList copy(){
  DoubleList outgoing=new DoubleList(data);
  outgoing.count=count;
  return outgoing;
}
