/** 
 * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
 */
public boolean insert(int val){
  boolean status=map.containsKey(val);
  Set<Integer> set=map.get(val);
  if (set == null) {
    set=new HashSet<>();
    map.put(val,set);
  }
  list.add(val);
  set.add(list.size() - 1);
  return !status;
}
