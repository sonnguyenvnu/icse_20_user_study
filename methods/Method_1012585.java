/** 
 * Inserts a value to the set. Returns true if the set did not already contain the specified element. 
 */
public boolean insert(int val){
  String key=Integer.toString(val);
  if (map.containsKey(key))   return false;
  nums.add(val);
  int index=nums.size() - 1;
  map.put(key,index);
  return true;
}
