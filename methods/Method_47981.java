/** 
 * Returns a new hint to be shown to the user. <p> The hint returned is marked as read on the list, and will not be returned again. In case all hints have already been read, and there is nothing left, returns null.
 * @return the next hint to be shown, or null if none
 */
public String pop(){
  int next=prefs.getLastHintNumber() + 1;
  if (next >= hints.length)   return null;
  prefs.updateLastHint(next,DateUtils.getToday());
  return hints[next];
}
