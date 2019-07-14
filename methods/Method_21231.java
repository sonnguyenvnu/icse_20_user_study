/** 
 * Gets the data object associated with a position.
 */
protected Object objectFromPosition(final int position){
  return objectFromSectionRow(sectionRowFromPosition(position));
}
