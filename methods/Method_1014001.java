/** 
 * Merge the given  {@link StateDescriptionFragment}. Set all unset ( {@code null}) fields of this instance to the values from the given  {@link StateDescriptionFragment}.
 * @param fragment a {@link StateDescriptionFragment} this instance should merge in.
 * @return this instance with the fields merged.
 */
public StateDescriptionFragment merge(StateDescriptionFragment fragment){
  if (this.minimum == null) {
    this.minimum=fragment.getMinimum();
  }
  if (this.maximum == null) {
    this.maximum=fragment.getMaximum();
  }
  if (this.step == null) {
    this.step=fragment.getStep();
  }
  if (this.pattern == null) {
    this.pattern=fragment.getPattern();
  }
  if (this.readOnly == null) {
    this.readOnly=fragment.isReadOnly();
  }
  if (this.options == null) {
    this.options=fragment.getOptions();
  }
  return this;
}
