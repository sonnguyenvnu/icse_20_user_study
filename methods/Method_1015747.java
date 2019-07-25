/** 
 * Returns the total count of all values 
 */
public int size(){
  int count=0;
  for (  Value val : map.values())   count+=val.count();
  return count;
}
