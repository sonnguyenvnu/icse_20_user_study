/** 
 * Returns all values associated with the name.
 */
public List<V> getAll(final String name){
  LinkedList<V> values=new LinkedList<>();
  int h=hash(name);
  int i=index(h);
  MapEntry<V> e=entries[i];
  while (e != null) {
    if (e.hash == h && eq(name,e.key)) {
      values.addFirst(e.getValue());
    }
    e=e.next;
  }
  return values;
}
