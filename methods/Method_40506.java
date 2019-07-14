/** 
 * Returns  {@code true} if this reference is a class instantiation.
 */
public void markAsCall(){
  flags|=CALL;
  flags&=~NEW;
}
