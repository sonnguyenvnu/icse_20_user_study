/** 
 * Interns the components of a name-and-type into this instance.
 * @param nat {@code non-null;} the name-and-type
 */
public synchronized void intern(CstNat nat){
  intern(nat.getName());
  intern(nat.getDescriptor());
}
