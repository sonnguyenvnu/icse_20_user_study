/** 
 * Adds a label to the item.
 * @param label the label to add
 * @param override whether the label should be added even if there is already a label in that language
 */
public ItemUpdateBuilder addLabel(MonolingualTextValue label,boolean override){
  Validate.isTrue(!built,"ItemUpdate has already been built");
  if (override) {
    labels.add(label);
  }
 else {
    labelsIfNew.add(label);
  }
  return this;
}
