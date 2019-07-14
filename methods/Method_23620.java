public FloatList copy(){
  FloatList outgoing=new FloatList(data);
  outgoing.count=count;
  return outgoing;
}
