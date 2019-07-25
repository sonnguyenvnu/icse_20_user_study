/** 
 * Create a builder instance and initialise all fields from the given  {@link StateDescription}. Note: State options will only be taken into account if the list is not empty.
 * @param legacy the {@link StateDescription} this builder be initialised from.
 * @return the builder.
 */
public static StateDescriptionFragmentBuilder create(StateDescription legacy){
  return new StateDescriptionFragmentBuilder(legacy);
}
