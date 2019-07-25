public static <K,V>Iterator<IEntry<K,V>> iterator(Node<K,V> root){
  if (root.size == 0) {
    return Iterators.EMPTY;
  }
  return new Iterator<IEntry<K,V>>(){
{
      stack[0]=root;
      nextValue();
    }
    private void nextValue(){
      while (depth >= 0) {
        Node<K,V> n=stack[depth];
switch (cursor[depth]) {
case 0:
          if (n.l.size == 0) {
            cursor[depth]++;
            return;
          }
 else {
            stack[++depth]=n.l;
            cursor[depth]=0;
          }
        break;
case 1:
      return;
case 2:
    if (n.r.size == 0) {
      if (--depth >= 0) {
        cursor[depth]++;
      }
    }
 else {
      stack[++depth]=n.r;
      cursor[depth]=0;
    }
  break;
case 3:
if (--depth >= 0) {
  cursor[depth]++;
}
}
}
}
@Override public boolean hasNext(){
return depth >= 0;
}
@Override public IEntry<K,V> next(){
Node<K,V> n=stack[depth];
IEntry<K,V> e=new Maps.Entry<>(n.k,n.v);
cursor[depth]++;
nextValue();
return e;
}
}
;
}
