/** 
 * Marks message with given flag.
 */
public ReceiverBuilder mark(final Flags.Flag flagToSet){
  this.flagsToSet.add(flagToSet);
  return this;
}
