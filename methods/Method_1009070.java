/** 
 * Returns <code>true</code> if the event is an ignorable white space, regardless of the characters that it matches.
 * @param e The event to compare with this event.
 * @return <code>true</code> if considered equal;<code>false</code> otherwise.
 */
@Override public boolean equals(DiffXEvent e){
  if (this == e)   return true;
  if (e.getClass() != this.getClass())   return false;
  return true;
}
