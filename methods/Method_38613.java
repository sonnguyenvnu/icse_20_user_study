/** 
 * Adds props files or patterns.
 */
@Override public JoyProps addPropsFile(final String namePattern){
  requireNotStarted(props);
  this.propsNamePatterns.add(namePattern);
  return this;
}
