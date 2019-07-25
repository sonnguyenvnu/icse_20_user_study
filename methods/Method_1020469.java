@SuppressWarnings("unchecked") public static <T extends Cloneable<?>>List<T> clone(List<T> nodes){
  return nodes.stream().map(node -> (T)node.clone()).collect(toImmutableList());
}
