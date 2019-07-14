public List<V> getAllValue(){
  List<V> values=new ArrayList<>(children != null ? children.size() + 1 : 1);
  values.add(value);
  if (null != children) {
    children.stream().map(TreeNode::getAllValue).flatMap(List::stream).forEach(values::add);
  }
  return values;
}
