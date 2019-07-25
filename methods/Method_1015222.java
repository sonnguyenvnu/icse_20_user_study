public static <V>IList<Node<V>> split(Object editor,Node<V> node,int targetSize){
  IList<Node<V>> result=new LinearList<>();
  if ((node.size() >> 1) < targetSize) {
    result.addLast(node);
  }
 else {
    Node<V> acc=new Node<>(editor,node.prefix,node.offset);
    PrimitiveIterator.OfInt masks=Util.masks(node.datamap | node.nodemap);
    while (masks.hasNext()) {
      int mask=masks.nextInt();
      if (acc.size() >= targetSize) {
        result.addLast(acc);
        acc=new Node<>(editor,node.prefix,node.offset);
      }
      if (node.isEntry(mask)) {
        acc=transferEntry(mask,node,acc);
      }
 else       if (node.isNode(mask)) {
        Node<V> child=node.node(mask);
        if (child.size() >= (targetSize << 1)) {
          split(editor,child,targetSize).forEach(result::addLast);
        }
 else {
          acc=acc.putNode(mask,child);
        }
      }
    }
    if (acc.size() > 0) {
      result.addLast(acc);
    }
  }
  return result;
}
