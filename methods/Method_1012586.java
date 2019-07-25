/** 
 * Inserts a value to the set. Returns true if the set did not already contain the specified element. 
 */
public boolean insert(int val){
  String key=Integer.toString(val);
  if (map.contains(key))   return false;
  nums.add(val);
  int index=nums.size() - 1;
  map.add(key,index);
  return true;
}
