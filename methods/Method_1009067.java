/** 
 * Returns <code>true</code> if the event is an attribute event.
 * @param e The event to compare with this event.
 * @return <code>true</code> if this event is equal to the specified event;<code>false</code> otherwise.
 */
@Override public boolean equals(DiffXEvent e){
  if (e.getClass() != this.getClass())   return false;
  AttributeEventNSImpl ae=(AttributeEventNSImpl)e;
  if (!ae.name.equals(this.name))   return false;
  if (!equals(ae.uri,this.uri))   return false;
  if (!ae.value.equals(this.value))   return false;
  return true;
}
