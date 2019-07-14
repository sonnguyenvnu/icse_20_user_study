/** 
 * @param head
 * @return the current index of dependents
 */
public int valence(int head){
  return rightValency(head) + leftValency(head);
}
