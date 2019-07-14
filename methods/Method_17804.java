/** 
 * Adds an item to a possibly null list to defer the allocation as long as possible.
 * @param list the nullable list.
 * @param item the item to add.
 */
public static <A>List<A> addOrCreateList(@Nullable List<A> list,A item){
  if (list == null) {
    list=new LinkedList<>();
  }
  list.add(item);
  return list;
}
