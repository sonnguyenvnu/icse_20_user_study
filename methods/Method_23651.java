public IntList copy(){
  IntList outgoing=new IntList(data);
  outgoing.count=count;
  return outgoing;
}
