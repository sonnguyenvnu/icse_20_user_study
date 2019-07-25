/** 
 * Merge commands
 * @param other the other command to merge to
 * @return merge result, by default returns this instance.
 */
public Command merge(Command other){
  mergedCommands.add(other);
  return this;
}
