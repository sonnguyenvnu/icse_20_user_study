public VertexArrayList toVertexArrayList(){
  VertexArrayList list=new VertexArrayList(tx);
  for (int i=0; i < vertices.size(); i++) {
    list.add(get(i));
  }
  return list;
}
