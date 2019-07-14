public StringList copy(){
  StringList outgoing=new StringList(data);
  outgoing.count=count;
  return outgoing;
}
