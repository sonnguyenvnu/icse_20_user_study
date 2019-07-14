public List<V> getAllValue(Predicate<TreeNode<V>> filter){
  List<V> values=new ArrayList<>(getChildren().size() + 1);
  if (filter.test(this)) {
    values.add(value);
  }
  children.stream().filter(filter).map(val -> val.getAllValue(filter)).flatMap(List::stream).forEach(values::add);
  return values;
}
