/** 
 * Adds custom button into currently used buttons group.
 * @param button custom button to add into buttons group
 * @return buttons group used for grouping
 */
public UnselectableButtonGroup group(final AbstractButton button){
  if (group != null) {
    group.add(button);
  }
  return group;
}
