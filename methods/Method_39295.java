/** 
 * Unmarks a message with given flag.
 */
public ReceiverBuilder unmark(final Flags.Flag flagToUnset){
  this.flagsToUnset.add(flagToUnset);
  return this;
}
