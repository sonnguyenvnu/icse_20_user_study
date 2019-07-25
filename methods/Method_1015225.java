public static <K,V>Node<K,V> intersection(int shift,Object editor,Node<K,V> a,Node<K,V> b,BiPredicate<K,K> equals){
  Node<K,V> result=new Node<K,V>(editor);
  PrimitiveIterator.OfInt masks=Util.masks((a.nodemap | a.datamap) & (b.nodemap | b.datamap));
  while (masks.hasNext()) {
    int mask=masks.nextInt();
    int state=mergeState(mask,a.nodemap,a.datamap,b.nodemap,b.datamap);
    int idx, ia, ib;
switch (state) {
case ENTRY_ENTRY:
      ia=a.entryIndex(mask);
    ib=b.entryIndex(mask);
  if (b.hashes[ib] == a.hashes[ia] && equals.test((K)b.content[ib << 1],(K)a.content[ia << 1])) {
    result=transferEntry(mask,a,result);
  }
break;
case NODE_NODE:
INode<K,V> n=intersectNodes(shift + 5,editor,a.node(mask),b.node(mask),equals);
if (n != null) {
result=result.putNode(mask,n);
}
break;
case NODE_ENTRY:
idx=b.entryIndex(mask);
int hash=b.hashes[idx];
K key=(K)b.content[idx << 1];
Object val=get(a,shift,hash,key,equals,DEFAULT_VALUE);
if (val != DEFAULT_VALUE) {
result=result.put(shift,editor,hash,key,(V)val,equals,null);
}
break;
case ENTRY_NODE:
idx=a.entryIndex(mask);
if (get(b,shift,a.hashes[idx],(K)a.content[idx << 1],equals,DEFAULT_VALUE) != DEFAULT_VALUE) {
result=transferEntry(mask,a,result);
}
break;
default :
throw new IllegalStateException();
}
}
return result.size() > 0 ? result : null;
}
