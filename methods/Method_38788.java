/** 
 * Matches node to this selector.
 */
@Override public boolean accept(final Node node){
  return pseudoClass.match(node);
}
