/** 
 * Records cache insertion and updates.
 * @param count the number of writes to record
 */
public void recordPuts(@NonNegative long count){
  if (enabled && (count != 0)) {
    puts.add(count);
  }
}
