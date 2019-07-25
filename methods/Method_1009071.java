/** 
 * Returns <code>true</code> if the event is a open element event.
 * @param e The event to compare with this event.
 * @return <code>true</code> if this event is equal to the specified event;<code>false</code> otherwise.
 */
@Override public boolean equals(DiffXEvent e){
  if (e == null)   return false;
  if (e.getClass() != this.getClass())   return false;
  OpenElementEventNSImpl oee=(OpenElementEventNSImpl)e;
  if (!oee.uri.equals(this.uri))   return false;
  if (!oee.name.equals(this.name))   return false;
  return true;
}
