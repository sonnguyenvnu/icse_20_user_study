/** 
 * Returns the size of this constant.
 * @return the size of this constant, i.e., 2 for {@code long} and {@code double}, 1 otherwise.
 */
public int getSize(){
  char firstCharOfDescriptor=descriptor.charAt(0);
  return (firstCharOfDescriptor == 'J' || firstCharOfDescriptor == 'D') ? 2 : 1;
}
