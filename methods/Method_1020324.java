/** 
 * Clear the list
 */
public void clear(){
  head.remove();
  tail.remove();
  head.insertBefore(tail);
}
