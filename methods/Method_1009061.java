/** 
 * Removes the last element from the top of the stack.
 * @return The last element from the top of the stack.
 */
public OpenElementEvent pop(){
  if (this.size > 0) {
    this.size--;
    return this.openElements[this.size];
  }
  return null;
}
