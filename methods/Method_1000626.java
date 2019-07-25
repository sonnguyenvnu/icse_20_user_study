public List<Object> values(){
  List<Object> re=new ArrayList<Object>(nodes.size());
  for (  SegmentNode node : nodes) {
    if (node.isKey())     re.add(context.get(node.getValue()));
 else     re.add(node.getValue());
  }
  return re;
}
