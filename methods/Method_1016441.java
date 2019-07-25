/** 
 * memory that is available including increasing total memory up to maximum
 * @return bytes
 */
public static final long available(){
  return maxMemory() - total() + free();
}
