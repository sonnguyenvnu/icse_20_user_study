public List<Node> build(){
  buildTree(root);
  List<Node> result=new ArrayList<Node>();
  result.add(root);
  return result;
}
