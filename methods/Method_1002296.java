/** 
 * Get and clear the current count of reprobes.  Reprobes happen on key collisions, and a high reprobe rate may indicate a poor hash function or weaknesses in the table resizing function.
 * @return the count of reprobes since the last call to {@link #reprobes}or since the table was created.   
 */
public long reprobes(){
  long r=_reprobes.get();
  _reprobes=new ConcurrentAutoTable();
  return r;
}
