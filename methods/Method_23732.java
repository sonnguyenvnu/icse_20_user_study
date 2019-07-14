/** 
 * Create a dictionary associating each entry in this list to its index. 
 */
public IntDict getOrder(){
  IntDict outgoing=new IntDict();
  for (int i=0; i < count; i++) {
    outgoing.set(data[i],i);
  }
  return outgoing;
}
