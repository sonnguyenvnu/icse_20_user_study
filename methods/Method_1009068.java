/** 
 * Returns <code>true</code> if the open element has the same name. {@inheritDoc}
 */
@Override public boolean match(OpenElementEvent event){
  if (event == null)   return false;
  if (event == this.open)   return true;
  return event.getName().equals(getName());
}
