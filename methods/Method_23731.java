/** 
 * Count the number of times each String entry is found in this list. 
 */
public IntDict getTally(){
  IntDict outgoing=new IntDict();
  for (int i=0; i < count; i++) {
    outgoing.increment(data[i]);
  }
  return outgoing;
}
