/** 
 * The length of this list.
 * @return The length of this list.
 */
public final int length(){
  return foldLeft((i,a) -> i + 1,0);
}
