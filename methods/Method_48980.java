public VertexLongList toVertexLongList(){
  LongArrayList list=toLongList(vertices);
  return new VertexLongList(tx,list,sorted);
}
