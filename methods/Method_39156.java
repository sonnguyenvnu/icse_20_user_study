/** 
 * Acquires action from Petite container.
 */
@Override @SuppressWarnings({"unchecked"}) protected Object createAction(final Class actionClass){
  return petiteContainer.createBean(actionClass);
}
