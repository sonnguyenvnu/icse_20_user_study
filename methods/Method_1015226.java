public static <K,V>IList<Node<K,V>> split(Object editor,Node<K,V> node,int targetSize){
  IList<Node<K,V>> result=new LinearList<>();
  if ((node.size() >> 1) < targetSize) {
    result.addLast(node);
  }
 else {
    Node<K,V> acc=new Node<>(editor);
    PrimitiveIterator.OfInt masks=Util.masks(node.datamap | node.nodemap);
    while (masks.hasNext()) {
      int mask=masks.nextInt();
      if (acc.size() >= targetSize) {
        result.addLast(acc);
        acc=new Node<>(editor);
      }
      if (node.isEntry(mask)) {
        acc=transferEntry(mask,node,acc);
      }
 else       if (node.isNode(mask)) {
        INode<K,V> child=node.node(mask);
        if (child instanceof Node && child.size() >= (targetSize << 1)) {
          split(editor,(Node<K,V>)child,targetSize).stream().map(n -> new Node<K,V>(editor).putNode(mask,n)).forEach(result::addLast);
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
