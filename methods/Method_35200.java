/** 
 * Returns the current backstack, ordered from root to most recently pushed.
 */
@NonNull public List<RouterTransaction> getBackstack(){
  List<RouterTransaction> list=new ArrayList<>(backstack.size());
  Iterator<RouterTransaction> backstackIterator=backstack.reverseIterator();
  while (backstackIterator.hasNext()) {
    list.add(backstackIterator.next());
  }
  return list;
}
