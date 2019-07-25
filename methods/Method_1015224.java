public static <K,V>boolean contains(Node<K,V> node,int shift,int hash,K key,BiPredicate<K,K> equals){
  return get(node,shift,hash,key,equals,DEFAULT_VALUE) != DEFAULT_VALUE;
}
