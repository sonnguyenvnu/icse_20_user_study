/** 
 * Finds the position of the given model in the list. Doesn't use indexOf to avoid unnecessary equals() calls since we're looking for the same object instance.
 * @return The position of the given model in the current models list, or -1 if the model can't befound.
 */
protected int getModelPosition(EpoxyModel<?> model){
  int size=getCurrentModels().size();
  for (int i=0; i < size; i++) {
    if (model == getCurrentModels().get(i)) {
      return i;
    }
  }
  return -1;
}
