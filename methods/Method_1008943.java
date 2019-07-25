/** 
 * Walks the Tree in pre-order style. This is a recursive method, and is called from the toList() method with the root element as the first argument. It appends to the second argument, which is passed by reference      as it recurses down the tree.
 * @param element the starting element.
 * @param list the output of the walk.
 */
private void walk(Node<T> element,List<Node<T>> list){
  if (element == null) {
    return;
  }
  list.add(element);
  for (  Node<T> data : element.getChildren()) {
    walk(data,list);
  }
}
