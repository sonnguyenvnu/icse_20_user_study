/** 
 * @throws IndexOutOfBoundsException If the given position is out of range of the current modellist.
 */
@NonNull public EpoxyModel<?> getModelAtPosition(int position){
  return getCurrentModels().get(position);
}
