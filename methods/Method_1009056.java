/** 
 * Returns <code>true</code> if this list contains the specified element.
 * @param element Element whose presence is to be tested.
 * @return  <code>true</code> if the specified element is present;<code>false</code> otherwise.
 */
public boolean contains(OpenElementEvent element){
  return indexOf(element) >= 0;
}
