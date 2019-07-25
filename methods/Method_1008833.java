/** 
 * Used to avoid http://bugs.sun.com/view_bug.do?bug_id=6558557 
 */
static <T>Multiset<T> cast(Iterable<T> iterable){
  return (Multiset<T>)iterable;
}
