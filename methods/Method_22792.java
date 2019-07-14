/** 
 * Returns the line containing the specified offset.
 * @param offset The offset
 */
public final int getLineOfOffset(int offset){
  return document.getDefaultRootElement().getElementIndex(offset);
}
