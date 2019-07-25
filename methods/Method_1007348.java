/** 
 * Defrosts the class so that the class can be modified again. <p>To avoid changes that will be never reflected, the class is frozen to be unmodifiable if it is loaded or written out.  This method should be called only in a case that the class will be reloaded or written out later again. <p>If <code>defrost()</code> will be called later, pruning must be disallowed in advance.
 * @see #isFrozen()
 * @see #stopPruning(boolean)
 * @see #detach()
 */
public void defrost(){
  throw new RuntimeException("cannot defrost " + getName());
}
