/** 
 * pop the head block then remove it.
 */
public boolean pop(){
  KhaosBlock prev=head.getParent();
  if (prev != null) {
    head=prev;
    return true;
  }
  return false;
}
